package br.com.nubankmobileexercise;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.nubankmobileexercise.Api.General.Response.Link;
import br.com.nubankmobileexercise.Api.General.Response.LinkNotice;
import br.com.nubankmobileexercise.Api.General.Response.LinksResponseNotice;

/**
 * Created by Paulo Vitor on 25/03/2016.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class NoticeTest {

    @Test(expected = IllegalArgumentException.class)
    public void LinkHrefValueNull(){
        Link link = new Link("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void LinkNoticeNull(){
        LinkNotice linkNotice = new LinkNotice(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void LinksResponseNoticeNull(){
        LinkNotice linkNotice = null;
        LinksResponseNotice linksResponseNotice = new LinksResponseNotice(linkNotice);
    }

    @Test
    public void LinkHrefValueNotNull(){
        Link link = new Link("http://www.teste.com.br");
    }

    @Test
    public void LinkNoticeNotNull(){
        Link link = new Link("http://www.teste.com.br");
        LinkNotice linkNotice = new LinkNotice(link);
    }

    @Test
    public void LinksResponseNoticeNotNull(){
        Link link = new Link("http://www.teste.com.br");
        LinkNotice linkNotice = new LinkNotice(link);
        LinksResponseNotice linksResponseNotice = new LinksResponseNotice(linkNotice);
    }

    @Test
    public void LinkIsAValidURL(){
        Link link = new Link("http://www.teste.com.br");

        Assert.assertTrue(link.isValidUrl());
    }
}

