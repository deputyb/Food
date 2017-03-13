package com.wegmans.aem.wegmans.models

import com.twcable.jackalope.impl.sling.SimpleResourceResolverFactory

import static com.twcable.jackalope.JCRBuilder.*
import com.wegmans.components.news.NewsBlockModel

class NewsBlockModelTest extends GroovyTestCase {
    void testGetHeadlines() {
        def repo = buildRepo()
        def resolverFactory = new SimpleResourceResolverFactory(repo)
        def resolver = resolverFactory.getAdministrativeResourceResolver(null);
        def componentResource = resolver.getResource("/content/component-resource")
        assert componentResource != null

        assert resolver.getResource("/etc") != null
        assert resolver.getResource("/etc/assets") != null
        assert resolver.getResource("/etc/assets/scaffolding") != null
        assert resolver.getResource(NewsBlockModel.NEWS_JCR_PATH) != null


        def model = new NewsBlockModel(componentResource)

        def EXPECTED_SIZE = 4
        model.maxElements = EXPECTED_SIZE

        assert "News Block" == model.getTitle()


        def headlines = model.getHeadlines()

        assert EXPECTED_SIZE == headlines.size()

        // Unfortunately I couldn't find a way for
        // Jackalope to preserve the order so here
        // we are...
        assert headlines.contains("Second Headline")
        assert headlines.contains("First Headline")
        assert headlines.contains("Third Headline")
        assert headlines.contains("Fourth Headline")

    }

    static def buildRepo() {
        return repository(
            node("content",
                node("component-resource")),
            node("etc",
                node("assets",
                    node("scaffolding",
                        node("news",
                            node("news-item-1",
                                node("jcr:content",
                                    property("jcr:title", "First Headline"))),
                            node("news-item-2",
                                node("jcr:content",
                                    property("jcr:title", "Second Headline"))),
                            node("news-item-3",
                                node("jcr:content",
                                    property("jcr:title", "Third Headline"))),
                            node("news-item-4",
                                node("jcr:content",
                                    property("jcr:title", "Fourth Headline")))))))).build()

    }

}
