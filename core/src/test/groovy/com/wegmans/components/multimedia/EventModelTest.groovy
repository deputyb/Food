package com.wegmans.aem.wegmans.models

import com.twcable.jackalope.impl.sling.SimpleResourceResolverFactory

import static com.twcable.jackalope.JCRBuilder.*
import com.wegmans.components.multimedia.EventModel

class EventModelTest extends GroovyTestCase {
    void testGetEvents() {
        def repo = buildRepo()
        def resolverFactory = new SimpleResourceResolverFactory(repo)
        def resolver = resolverFactory.getAdministrativeResourceResolver(null);
        def componentResource = resolver.getResource("/content/event_resource")
        assert componentResource != null

        assert resolver.getResource("/content/event_resource") != null
        assert resolver.getResource("/content/event_resource/eventPath") != null

        assert resolver.getResource("/etc") != null
        assert resolver.getResource("/etc/assets") != null
        assert resolver.getResource("/etc/assets/scaffolding") != null
        assert resolver.getResource("/etc/assets/scaffolding/events") != null
        assert resolver.getResource("/etc/assets/scaffolding/events/events-item-1") != null

        def assetResource = resolver.getResource("/etc/assets/scaffolding/events/events-item-1/jcr:content")


        def model = new EventModel(componentResource, assetResource)

        assert "First Event" == model.getEventTitle()
        assert "Sub Title here" == model.getEventSubTitle()
        assert "Detail here" == model.getEventDetail()


    }

    static def buildRepo() {
        return repository(
            node("content",
                node("event_resource",
                        property("eventPath", "/etc/assets/scaffolding/events/events-item-1"))),
            node("etc",
                node("assets",
                    node("scaffolding",
                        node("events",
                            node("events-item-1",
                                node("jcr:content",
                                    property("jcr:title", "First Event"),
                                    property("subTitle", "Sub Title here"),
                                        property("detail", "Detail here")))))))).build()

    }

}
