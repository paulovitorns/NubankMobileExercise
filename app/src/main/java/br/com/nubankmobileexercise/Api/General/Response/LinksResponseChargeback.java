package br.com.nubankmobileexercise.Api.General.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Paulo on 10/03/2016.
 */
public class LinksResponseChargeback {

    @SerializedName("block_card")
    private Link blockcard;

    @SerializedName("unblock_card")
    private Link unblockcard;

    @SerializedName("self")
    private Link self;

    public Link getBlockcard() {
        return blockcard;
    }

    public void setBlockcard(Link blockcard) {
        this.blockcard = blockcard;
    }

    public Link getUnblockcard() {
        return unblockcard;
    }

    public void setUnblockcard(Link unblockcard) {
        this.unblockcard = unblockcard;
    }

    public Link getSelf() {
        return self;
    }

    public void setSelf(Link self) {
        this.self = self;
    }

}
