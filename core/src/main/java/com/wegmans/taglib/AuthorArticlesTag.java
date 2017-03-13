package com.wegmans.taglib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.wegmans.components.util.Constants;

public class AuthorArticlesTag extends CQBaseTag {

    private static final long serialVersionUID = 1L;
    protected static Logger log = LoggerFactory.getLogger(AuthorArticlesTag.class);
    private String authorPath;

    public void setAuthorPath(String authorPath) {
        this.authorPath = authorPath;
    }

    @Override
    public int doStartTag() throws JspException {

        ArrayList<String> articles = new ArrayList<>();
        Map<String, String> predicateMap = new HashMap<>();
        try {

            predicateMap.put("path", Constants.ARTICLE_ASSETS_PATH);
            predicateMap.put("property", "jcr:author");
            predicateMap.put("property.value", authorPath);

            PredicateGroup predicateGroup = PredicateGroup.create(predicateMap);
            QueryBuilder queryBuilder = resourceResolver.adaptTo(QueryBuilder.class);
            Session session = resourceResolver.adaptTo(Session.class);
            Query query = queryBuilder.createQuery(predicateGroup, session);

            SearchResult result = query.getResult();

            for (Hit hit : result.getHits()) {
                articles.add(hit.getNode().getProperty("jcr:title").getString());
            }

        } catch (RepositoryException e) {
            log.error(e.getMessage());
        }

        if (articles.size() > 4) {
            articles = (ArrayList<String>) articles.subList(0, 3);
        }

        pageContext.setAttribute("articles", articles);

        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

}
