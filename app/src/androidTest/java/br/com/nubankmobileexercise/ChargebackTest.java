package br.com.nubankmobileexercise;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import br.com.nubankmobileexercise.Api.General.Response.ChargeBackResponse;
import br.com.nubankmobileexercise.Api.General.Response.Link;
import br.com.nubankmobileexercise.Api.General.Response.LinkNotice;
import br.com.nubankmobileexercise.Api.General.Response.LinksResponseChargeback;
import br.com.nubankmobileexercise.Api.General.Response.LinksResponseNotice;
import br.com.nubankmobileexercise.Api.General.Response.ReasonDetails;

/**
 * Created by Paulo Vitor on 25/03/2016.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class ChargebackTest {

    @Test(expected = IllegalArgumentException.class)
    public void LinksResponseChargebackNull(){
        Link blockcard = null;
        Link unblockcard = null;
        Link self = null;
        LinksResponseChargeback links = new LinksResponseChargeback(blockcard, unblockcard, self);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ReasonDetailsNull(){

        ReasonDetails reason_details = new ReasonDetails("", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ChargeBackResponseNull(){

        List<ReasonDetails> reason_details = null;
        LinksResponseChargeback links = new LinksResponseChargeback();

        ChargeBackResponse chargeBackResponse = new ChargeBackResponse("", "", "", true, reason_details, links);
    }

    @Test
    public void LinksResponseChargebackNotNull(){
        Link blockcard = new Link("http://www.linkblock.com.br");
        Link unblockcard = new Link("http://www.linkunblock.com.br");
        Link self = new Link("http://www.linkself.com.br");
        LinksResponseChargeback links = new LinksResponseChargeback(blockcard, unblockcard, self);
    }

    @Test
    public void ReasonDetailsNotNull(){

        ReasonDetails reason_details = new ReasonDetails("reason_id", "reason description");
    }

    @Test
    public void ChargeBackResponseNotNull(){

        ReasonDetails reason = new ReasonDetails("reason_id", "reason description");
        ReasonDetails reason2 = new ReasonDetails("reason_id", "reason description");

        List<ReasonDetails> reason_details = new ArrayList<>();
        reason_details.add(reason);
        reason_details.add(reason2);

        Link blockcard = new Link("http://www.linkblock.com.br");
        Link unblockcard = new Link("http://www.linkunblock.com.br");
        Link self = new Link("http://www.linkself.com.br");
        LinksResponseChargeback links = new LinksResponseChargeback(blockcard, unblockcard, self);

        ChargeBackResponse chargeBackResponse = new ChargeBackResponse("Comentário de teste", "chargeback_id", "ChargBack Title", true, reason_details, links);
    }


    @Test
    public void LinkIsAValidURL(){
        ReasonDetails reason = new ReasonDetails("reason_id", "reason description");
        ReasonDetails reason2 = new ReasonDetails("reason_id", "reason description");

        List<ReasonDetails> reason_details = new ArrayList<>();
        reason_details.add(reason);
        reason_details.add(reason2);

        Link blockcard = new Link("http://www.linkblock.com.br");
        Link unblockcard = new Link("http://www.linkunblock.com.br");
        Link self = new Link("http://www.linkself.com.br");
        LinksResponseChargeback links = new LinksResponseChargeback(blockcard, unblockcard, self);

        ChargeBackResponse chargeBackResponse = new ChargeBackResponse("Comentário de teste", "chargeback_id", "ChargBack Title", true, reason_details, links);

        Assert.assertTrue(chargeBackResponse.getLinks().getBlockcard().isValidUrl());
        Assert.assertTrue(chargeBackResponse.getLinks().getUnblockcard().isValidUrl());
        Assert.assertTrue(chargeBackResponse.getLinks().getSelf().isValidUrl());
    }

}

