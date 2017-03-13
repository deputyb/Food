package com.wegmans.components.products;



import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import com.wegmans.components.validation.AbstractValidatableModel;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Model(adaptables = {SlingHttpServletRequest.class, Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FeaturedProductsBean extends AbstractValidatableModel {

    private static final String FEATURED_PRODUCTS_EMPTY_VALIDATION_MESSAGE = "Featured Product Id must not be empty, please fill it in the dialog";


    @Inject
    private String[] featuredProductIds;

	public String[] getFeaturedProductIds() {
		return featuredProductIds;
	}

	public void setFeaturedProductIds(String[] featuredProductIds) {
		this.featuredProductIds = featuredProductIds;
	}
    
	@PostConstruct
    public void initialize()
    {
    	validate();
    }
	
	@Override
	protected void validate()
	{
		if (null == featuredProductIds || featuredProductIds.length == 0) {
            addErrorMessage(FEATURED_PRODUCTS_EMPTY_VALIDATION_MESSAGE);
            return;
        }
	}
	
	@Override
    public boolean isValid() {	
        return super.isValid();
    }

 
}