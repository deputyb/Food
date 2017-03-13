package com.wegmans.service;

/**
 * Service used for configuring access to our REST API.
 * 
 * @author jakub.malecki
 */
public interface ApiConfigurationService {

    String getApiInstanceUrl();

    String getApiVersion();

    String getProductsEndpointUrl();

    String getRecipesEndpointUrl();

    String getContentEndpointUrl();

    String getSearchEndpointUrl();

    String getPurchasesEndpointUrl();

    String getFullProductsEndpointUrl();

    String getFullRecipesEndpointUrl();

    String getFullContentEndpointUrl();

    String getFullSearchEndpointUrl();

    String getFullPurchasesEndpointUrl();
}
