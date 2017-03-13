package com.wegmans.components.global;

import com.wegmans.components.globals.NavigationItemModel;
import com.wegmans.components.globals.NavigationModel;
import org.apache.sling.api.resource.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.wcm.testing.mock.aem.junit.AemContext;

public class NavigationModelTest {

    @Rule
    public final AemContext context = new AemContext();

    @Before
    public void setUp() throws Exception {
        context.addModelsForPackage("com.wegmans.components.globals");
        context.load().json("/navigation-test.json", "/content/wegmans/en");
    }

    @Test
    public void testBasic() {
        Resource navigationResource = context.resourceResolver().getResource("/content/wegmans/en/jcr:content/footer");
        Assert.assertNotNull(navigationResource);

        NavigationModel navigationModel = navigationResource.adaptTo(NavigationModel.class);

        Assert.assertNotNull(navigationModel);
        Assert.assertNotNull(navigationModel.getItems());
        Assert.assertEquals(2, navigationModel.getItems().size());

        NavigationItemModel item1 = navigationModel.getItems().get(0);
        NavigationItemModel item2 = navigationModel.getItems().get(1);

        Assert.assertEquals("/content/geometrixx-outdoors-mobile/en/contact", item1.getPagePath());
        Assert.assertEquals("/content/geometrixx-outdoors/en/women", item2.getPagePath());
    }
}
