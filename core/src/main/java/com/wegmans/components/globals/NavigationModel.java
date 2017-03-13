package com.wegmans.components.globals;

import java.util.List;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import com.wegmans.components.validation.AbstractValidatableModel;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class NavigationModel extends AbstractValidatableModel {

    @Inject
    private List<NavigationItemModel> items;

    public List<NavigationItemModel> getItems() {
        return items;
    }

    @Override
    protected void validate() {}

    @Override
    public boolean isValid() {
        if (items != null) {
            for (NavigationItemModel item : items) {
                if (!item.isValid()) {
                    return false;
                }
            }
        }

        return true;
    }
}
