package com.example.jungle.weixin.Bean.XHRBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by derrickJ on 2017/11/26.
 */

public class XHRHotStatus {

    private CardListInfo cardListInfo;
    private List<XHRCard> cards = new ArrayList<>();

    public XHRHotStatus(CardListInfo cardListInfo, List<XHRCard> cards) {
        this.cardListInfo = cardListInfo;
        this.cards = cards;
    }

    public CardListInfo getCardListInfo() {
        return cardListInfo;
    }

    public void setCardListInfo(CardListInfo cardListInfo) {
        this.cardListInfo = cardListInfo;
    }

    public List<XHRCard> getCards() {
        return cards;
    }

    public void setCards(List<XHRCard> cards) {
        this.cards = cards;
    }

    public class CardListInfo {

        private int total;
        private int page;

        public CardListInfo(int total, int page) {
            this.total = total;
            this.page = page;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }
    }

}
