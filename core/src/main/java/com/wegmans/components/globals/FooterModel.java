package com.wegmans.components.globals;

import com.wegmans.components.globals.InheritedNavigationModel;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import com.wegmans.components.util.Constants;

@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FooterModel extends InheritedNavigationModel {

    @Override
    protected String getConfigurationFormat() {
        return Constants.FOOTER_CONFIGURATION_FORMAT;
    }
}
