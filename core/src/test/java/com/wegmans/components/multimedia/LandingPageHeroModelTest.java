package com.wegmans.components.multimedia;

import org.apache.sling.api.resource.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.wcm.testing.mock.aem.junit.AemContext;

public class LandingPageHeroModelTest {

    @Rule
    public final AemContext context = new AemContext();

    @Before
    public void setUp() throws Exception {
        context.addModelsForPackage("com.wegmans.components.multimedia");
        context.load().json("/landing-page-hero-test.json", "/content/wegmans/en");
    }

    @Test
    public void testWithInternalLink() {
        Resource landingPageHeroResource =
                context.resourceResolver().getResource("/content/wegmans/en/jcr:content/content/landing_page_hero");
        Assert.assertNotNull(landingPageHeroResource);

        LandingPageHeroModel heroModel = landingPageHeroResource.adaptTo(LandingPageHeroModel.class);

        Assert.assertNotNull(heroModel);
        Assert.assertFalse(heroModel.isBlank());
        Assert.assertEquals("The Freshest Produce", heroModel.getTitle());
        Assert.assertEquals("Regionally sourced vegetables from local farms.", heroModel.getSubtitle());
        Assert.assertEquals("Learn more", heroModel.getLinkButtonLabel());
        Assert.assertEquals("/content/geometrixx-outdoors/en/equipment", heroModel.getLinkButtonUrl());
        Assert.assertEquals("/content/geometrixx-outdoors/en/equipment.html", heroModel.getLinkButtonHref());
        Assert.assertEquals("/content/dam/geometrixx/banners/banner-web20.png", heroModel.getImage());
        Assert.assertEquals("layout2", heroModel.getLayout());
    }

    @Test
    public void testWithExternalLink() {
        Resource landingPageHeroResource =
                context.resourceResolver().getResource("/content/wegmans/en/jcr:content/content/landing_page_hero_585854445");
        Assert.assertNotNull(landingPageHeroResource);

        LandingPageHeroModel heroModel = landingPageHeroResource.adaptTo(LandingPageHeroModel.class);

        Assert.assertNotNull(heroModel);
        Assert.assertFalse(heroModel.isBlank());
        Assert.assertEquals("Title external", heroModel.getTitle());
        Assert.assertEquals("Subtitle external", heroModel.getSubtitle());
        Assert.assertEquals("Learn more", heroModel.getLinkButtonLabel());
        Assert.assertEquals("http://google.com", heroModel.getLinkButtonUrl());
        Assert.assertEquals("http://google.com", heroModel.getLinkButtonHref());
        Assert.assertEquals("/content/dam/geometrixx-media/ads/fremont_ad.png", heroModel.getImage());
        Assert.assertEquals("layout1", heroModel.getLayout());
    }
}
