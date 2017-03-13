/**
 * @author: Salvatore Randazzo
 */

/**
 * Handles the logic on what model or collection needs to be loaded for the specific view.
 *
 * @constructor
 */
class DataLoaded {

    /**
     * @param packageName
     */
    constructor (packageSpace) {
        this.package = packageSpace;
        this.apiConfigurations = null;
        this.currentScriptTag = document.querySelector('#api-script');
        this.collections = [];
        // Setting up the api configuration file
        this.setApiConfigurations(this.currentScriptTag);
        // Load all the backbone collections and models based on the apiConfigurations Object
        this.instantiateBackboneCollectionModels();
    }

    /**
     *
     */
    instantiateBackboneCollectionModels () {
        var currentBackboneCollection = null;
        // Checking if we have any collection available
        this.package.collections = this.package.collections || {};

        // Looping over all the collection to see if the Collection Name corresponds with any of the collection present
        _.forEach(this.apiConfigurations, (apiConfiguration) => {

            // Checking if the object with name collectionName exists in our wegmans global object
            if (!this.package.collections[apiConfiguration.collectionName]) {
                throw new Error('Collection not found for name provided: ' + apiConfiguration.collectionName);
            }

            // Instantiating the Backbone collection
            currentBackboneCollection = new this.package.collections[apiConfiguration.collectionName];
            // Creating a promise to see if the data is fetched from the View
            currentBackboneCollection.hasFetched = Q.defer();
            // Firing the get request to populate the API
            currentBackboneCollection.fetch({
                success: currentBackboneCollection.hasFetched.resolve,
                error: currentBackboneCollection.hasFetched.reject
            });

            // Adding the current collection to the collections list
            this.collections.push({
                name: apiConfiguration.collectionName,
                object: currentBackboneCollection
            });
        })
    }

    /**
     * @returns {apiConfigurations}
     */
    getApiConfigurations () {
        return this.apiConfigurations;
    }

    /**
     * @param DOMElement
     */
    setApiConfigurations (DOMElement) {
        this.apiConfigurations = JSON.parse(DOMElement.dataset.api);
    }

    /**
     * Generic to String method
     */
    toString () {
        // TODO: add toString element.
    }
}

var wegmans = wegmans || {};
wegmans.dataLoader = new DataLoaded(wegmans = wegmans || {});
