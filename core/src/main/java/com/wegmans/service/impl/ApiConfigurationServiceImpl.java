package com.wegmans.service.impl;

import java.util.Map;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.commons.osgi.PropertiesUtil;

import com.wegmans.service.ApiConfigurationService;

//@formatter:off
@Component(immediate = true, metatype = true, label = "REST API Configuration Service")
@Service(value = ApiConfigurationService.class)
@Properties({
  @Property(name = ApiConfigurationServiceImpl.API_INSTANCE_URL_PROPERTY, label = "REST API Instance URL", value = ApiConfigurationServiceImpl.DEFAULT_API_INSTANCE_URL),
  @Property(name = ApiConfigurationServiceImpl.API_VERSION_PROPERTY, label = "API version", value = ApiConfigurationServiceImpl.DEFAULT_API_VERSION),
  @Property(name = ApiConfigurationServiceImpl.PRODUCTS_ENDPOINT_URL_PROPERTY, label = "Products endpoint URL", value = ApiConfigurationServiceImpl.DEFAULT_PRODUCTS_ENDPOINT_URL),
  @Property(name = ApiConfigurationServiceImpl.RECIPES_ENDPOINT_URL_PROPERTY, label = "Recipes endpoint URL", value = ApiConfigurationServiceImpl.DEFAULT_RECIPES_ENDPOINT_URL),
  @Property(name = ApiConfigurationServiceImpl.CONTENT_ENDPOINT_URL_PROPERTY, label = "Content endpoint URL", value = ApiConfigurationServiceImpl.DEFAULT_CONTENT_ENDPOINT_URL),
  @Property(name = ApiConfigurationServiceImpl.SEARCH_ENDPOINT_URL_PROPERTY, label = "Search endpoint URL", value = ApiConfigurationServiceImpl.DEFAULT_SEARCH_ENDPOINT_URL),
  @Property(name = ApiConfigurationServiceImpl.PURCHASES_ENDPOINT_URL_PROPERTY, label = "Purchases endpoint URL", value = ApiConfigurationServiceImpl.DEFAULT_PURCHASES_ENDPOINT_URL)
})
//@formatter:on
public class ApiConfigurationServiceImpl implements ApiConfigurationService {

    static final String API_INSTANCE_URL_PROPERTY = "apiInstanceUrl";
    static final String DEFAULT_API_INSTANCE_URL = "http://localhost:11091";

    static final String API_VERSION_PROPERTY = "apiVersion";
    static final String DEFAULT_API_VERSION = "/v1";

    static final String PRODUCTS_ENDPOINT_URL_PROPERTY = "productsEndpointUrl";
    static final String DEFAULT_PRODUCTS_ENDPOINT_URL = "/products";

    static final String RECIPES_ENDPOINT_URL_PROPERTY = "recipesEndpointUrl";
    static final String DEFAULT_RECIPES_ENDPOINT_URL = "/recipes";

    static final String CONTENT_ENDPOINT_URL_PROPERTY = "contentEndpointUrl";
    static final String DEFAULT_CONTENT_ENDPOINT_URL = "/content";

    static final String SEARCH_ENDPOINT_URL_PROPERTY = "searchEndpointUrl";
    static final String DEFAULT_SEARCH_ENDPOINT_URL = "/search";

    static final String PURCHASES_ENDPOINT_URL_PROPERTY = "purchasesEndpointUrl";
    static final String DEFAULT_PURCHASES_ENDPOINT_URL = "/purchases";

    private String apiInstanceUrl;
    private String apiVersion;

    private String productsEndpointUrl;
    private String recipesEndpointUrl;
    private String contentEndpointUrl;
    private String searchEndpointUrl;
    private String purchasesEndpointUrl;

    @Activate
    public void activate(final Map<String, Object> properties) throws Exception {
        apiInstanceUrl = PropertiesUtil.toString(properties.get(API_INSTANCE_URL_PROPERTY), DEFAULT_API_INSTANCE_URL);
        apiVersion = PropertiesUtil.toString(properties.get(API_VERSION_PROPERTY), DEFAULT_API_VERSION);

        productsEndpointUrl = PropertiesUtil.toString(properties.get(PRODUCTS_ENDPOINT_URL_PROPERTY), DEFAULT_PRODUCTS_ENDPOINT_URL);
        recipesEndpointUrl = PropertiesUtil.toString(properties.get(RECIPES_ENDPOINT_URL_PROPERTY), DEFAULT_RECIPES_ENDPOINT_URL);
        contentEndpointUrl = PropertiesUtil.toString(properties.get(CONTENT_ENDPOINT_URL_PROPERTY), DEFAULT_CONTENT_ENDPOINT_URL);
        searchEndpointUrl = PropertiesUtil.toString(properties.get(SEARCH_ENDPOINT_URL_PROPERTY), DEFAULT_SEARCH_ENDPOINT_URL);
        purchasesEndpointUrl = PropertiesUtil.toString(properties.get(PURCHASES_ENDPOINT_URL_PROPERTY), DEFAULT_PURCHASES_ENDPOINT_URL);
    }

    @Override
    public String getApiInstanceUrl() {
        return apiInstanceUrl;
    }

    @Override
    public String getApiVersion() {
        return apiVersion;
    }

    @Override
    public String getProductsEndpointUrl() {
        return productsEndpointUrl;
    }

    @Override
    public String getRecipesEndpointUrl() {
        return recipesEndpointUrl;
    }

    @Override
    public String getContentEndpointUrl() {
        return contentEndpointUrl;
    }

    @Override
    public String getSearchEndpointUrl() {
        return searchEndpointUrl;
    }

    @Override
    public String getPurchasesEndpointUrl() {
        return purchasesEndpointUrl;
    }

    @Override
    public String getFullProductsEndpointUrl() {
        return apiInstanceUrl + apiVersion + productsEndpointUrl;
    }

    @Override
    public String getFullRecipesEndpointUrl() {
        return apiInstanceUrl + apiVersion + recipesEndpointUrl;
    }

    @Override
    public String getFullContentEndpointUrl() {
        return apiInstanceUrl + apiVersion + contentEndpointUrl;
    }

    @Override
    public String getFullSearchEndpointUrl() {
        return apiInstanceUrl + apiVersion + searchEndpointUrl;
    }

    @Override
    public String getFullPurchasesEndpointUrl() {
        return apiInstanceUrl + apiVersion + purchasesEndpointUrl;
    }
}
