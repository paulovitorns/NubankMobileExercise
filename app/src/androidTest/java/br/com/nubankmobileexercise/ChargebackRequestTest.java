package br.com.nubankmobileexercise;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import br.com.nubankmobileexercise.Api.General.Request.ChargebackRequest;
import br.com.nubankmobileexercise.Api.General.Request.ReasonDetailsRequest;

/**
 * Created by Paulo Vitor on 26/03/2016.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class ChargebackRequestTest {

    @Test(expected = IllegalArgumentException.class)
    public void ReasonDetailsRequestNull(){
        ReasonDetailsRequest reasonDetailsRequest = new ReasonDetailsRequest("", false);
    }

    @Test
    public void ReasonDetailsRequestNotNull(){
        ReasonDetailsRequest reasonDetailsRequest = new ReasonDetailsRequest("teste_reason", true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ChargebackRequestNull(){
        ChargebackRequest chargebackRequest = new ChargebackRequest("", null);
    }

    @Test
    public void ChargebackRequestNotNull(){

        List<ReasonDetailsRequest> reasonDetailsRequestList = new ArrayList<>();
        ReasonDetailsRequest reasonOne = new ReasonDetailsRequest("teste_reason_1", true);
        ReasonDetailsRequest reasonTwo = new ReasonDetailsRequest("teste_reason_2", false);

        reasonDetailsRequestList.add(reasonOne);
        reasonDetailsRequestList.add(reasonTwo);

        ChargebackRequest chargebackRequest = new ChargebackRequest("Teste objeto.", reasonDetailsRequestList);
    }



}
