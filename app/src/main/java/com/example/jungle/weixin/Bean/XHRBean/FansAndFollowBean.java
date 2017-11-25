package com.example.jungle.weixin.Bean.XHRBean;

import com.example.jungle.weixin.Bean.BaseBean.Status;
import com.example.jungle.weixin.Bean.BaseBean.User;
import com.example.jungle.weixin.PublicUtils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by derrickJ on 2017/11/25.
 */

public class FansAndFollowBean {

    private CardListInfo cardlistInfo;
    private List<Card> cards;

    public FansAndFollowBean(CardListInfo cardlistInfo, List<Card> cards) {
        this.cardlistInfo = cardlistInfo;
        this.cards = cards;
    }

    public CardListInfo getCardlistInfo() {
        return cardlistInfo;
    }

    public void setCardlistInfo(CardListInfo cardlistInfo) {
        this.cardlistInfo = cardlistInfo;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
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

    public class Card {

        private String title;
        private List<CardGroup> card_group = new ArrayList<>();

        public Card(String title, List<CardGroup> card_group) {
            this.title = title;
            this.card_group = card_group;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<CardGroup> getCard_group() {
            return card_group;
        }

        public void setCard_group(List<CardGroup> card_group) {
            this.card_group = card_group;
        }

    }

    public class CardGroup {

        private User user;

        public CardGroup(User user) {
            this.user = user;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

    }

}
