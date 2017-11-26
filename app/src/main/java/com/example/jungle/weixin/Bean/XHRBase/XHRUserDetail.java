package com.example.jungle.weixin.Bean.XHRBase;

import com.example.jungle.weixin.Bean.BaseBean.Status;
import com.example.jungle.weixin.Bean.BaseBean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by derrickJ on 2017/11/24.
 */

public class XHRUserDetail {

    private Info Info;
    private Content content;

    public XHRUserDetail(XHRUserDetail.Info info, Content content) {
        Info = info;
        this.content = content;
    }

    public XHRUserDetail.Info getInfo() {
        return Info;
    }

    public void setInfo(XHRUserDetail.Info info) {
        Info = info;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public class Info {

        private User userInfo;

        public Info(User userInfo) {
            this.userInfo = userInfo;
        }

        public User getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(User userInfo) {
            this.userInfo = userInfo;
        }
    }

    public class Content {

        private CardListInfo cardListInfo;
        private List<Card> cards = new ArrayList<>();

        public Content(CardListInfo cardListInfo, List<Card> cards) {
            this.cardListInfo = cardListInfo;
            this.cards = cards;
        }

        public CardListInfo getCardListInfo() {
            return cardListInfo;
        }

        public void setCardListInfo(CardListInfo cardListInfo) {
            this.cardListInfo = cardListInfo;
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

            private Status mblog = null;

            public Card(Status mblog) {
                this.mblog = mblog;
            }

            public Status getMblog() {
                return mblog;
            }

            public void setMblog(Status mblog) {
                this.mblog = mblog;
            }
        }

    }

}
