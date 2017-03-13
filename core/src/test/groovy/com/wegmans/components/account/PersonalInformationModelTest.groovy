package com.wegmans.aem.wegmans.models

import com.wegmans.components.account.PersonalInformationModel
import io.wcm.testing.mock.aem.junit.AemContext
import org.apache.sling.api.resource.Resource

class PersonalInformationModelTest extends GroovyTestCase {

    AemContext context = new AemContext();

    public PersonalInformationModelTest() {
        context.setUp()
        context.addModelsForPackage("com.wegmans.components.account");
        context.load().json("/personal-information-test.json", "/content/wegmans/en/my_account");
    }

    public void testSomething() {
        Resource componentResource = context.resourceResolver().getResource("/content/wegmans/en/my_account/jcr:content/personal_information");
        PersonalInformationModel model = componentResource.adaptTo(PersonalInformationModel.class);
        assert "/content/wegmans/en/my_account/edit.html" == model.getEditPageLink()
    }
}
