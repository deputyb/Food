package com.wegmans.taglib;

import com.day.cq.tagging.TagManager;
import com.day.cq.wcm.foundation.Search;
import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.jcr.RepositoryException;

public class SearchTag extends CQBaseTag {

    private static final long serialVersionUID = 1L;
    protected static Logger log = LoggerFactory.getLogger(SearchTag.class);

    @Override
    public int doStartTag() throws JspException {

        Search search = new Search(slingRequest);

        String searchIn = (String) properties.get("searchIn");
        String requestSearchPath = request.getParameter("path");
        if (searchIn != null) {
            if (requestSearchPath != null && requestSearchPath.startsWith(searchIn)) {
                search.setSearchIn(requestSearchPath);
            } else {
                search.setSearchIn(searchIn);
            }
        } else if (requestSearchPath != null) {
            search.setSearchIn(requestSearchPath);
        }

        final String escapedQuery = xssAPI.encodeForHTML(search.getQuery());
        final String escapedQueryForAttr = xssAPI.encodeForHTMLAttr(search.getQuery());
        final String escapedQueryForHref = xssAPI.getValidHref(search.getQuery());

        pageContext.setAttribute("escapedQuery", escapedQuery);
        pageContext.setAttribute("escapedQueryForAttr", escapedQueryForAttr);
        pageContext.setAttribute("escapedQueryForHref", escapedQueryForHref);

        pageContext.setAttribute("search", search);

        Search.Result result = null;
        try {
            result = search.getResult();
        } catch (RepositoryException e) {
            log.error(e.getMessage());
        }
        pageContext.setAttribute("result", result);

        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

}
