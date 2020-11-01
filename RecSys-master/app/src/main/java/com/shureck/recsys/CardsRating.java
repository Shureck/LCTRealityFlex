package com.shureck.recsys;

public class CardsRating {
    private Card card;
    private boolean isLike;

    public CardsRating(Card card, boolean isLike) {
        this.card = card;
        this.isLike = isLike;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }
}
