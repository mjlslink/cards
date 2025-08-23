package com.encl.cards.service.impl;

import com.encl.cards.constants.CardsConstants;
import com.encl.cards.dto.CardsDto;
import com.encl.cards.entities.Cards;
import com.encl.cards.exception.CardAlreadyExistsException;
import com.encl.cards.exception.CardNotFoundException;
import com.encl.cards.mapper.CardMapper;
import com.encl.cards.repository.CardsRepository;
import com.encl.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsService implements ICardsService {

    private CardsRepository cardsRepository;

    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> card = cardsRepository.findByMobileNumber(mobileNumber);
        if(card.isPresent()) {
            throw new CardAlreadyExistsException("Card already exists for mobile number: " + mobileNumber);
        }
        cardsRepository.save(createNewCard(mobileNumber));
    }

    /**
     * @param mobileNumber - Mobile Number of the Customer
     * @return the new card details
     */
    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);

        return newCard;
    }

    @Override
    public CardsDto fetchCard(String mobileNumber) {
        Optional<Cards> card = cardsRepository.findByMobileNumber(mobileNumber);
        CardsDto cardsDto = CardMapper.mapToDto(
                    card.orElseThrow( () -> new CardNotFoundException("Card not found for mobile number: " + mobileNumber)),
                    new CardsDto()
            );
        return cardsDto;
    }

    @Override
    public boolean updateCard(CardsDto cardsDto) {
        //first check if it exists
        Cards cards = cardsRepository.findByCardNumber(cardsDto.getCardNumber())
                .orElseThrow(() -> new CardNotFoundException("Card not found for card number: " + cardsDto.getCardNumber()));
        cardsRepository.save(CardMapper.mapToEntity(cardsDto, cards));
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new CardNotFoundException("Card not found for mobile number: " + mobileNumber));
        cardsRepository.delete(cards);
        return true;
    }
}
