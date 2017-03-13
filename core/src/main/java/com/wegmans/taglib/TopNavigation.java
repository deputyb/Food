package com.wegmans.taglib;

import com.day.cq.wcm.api.Page;
import com.wegmans.components.util.Constants;
import com.wegmans.components.globals.TopNavigationModel;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.jsp.JspException;
import org.apache.sling.api.resource.Resource;
import java.util.Iterator;
import javax.jcr.Node;
import javax.jcr.RepositoryException;


import static javax.servlet.jsp.tagext.Tag.SKIP_BODY;

public class TopNavigation extends CQBaseTag {

    private static final long serialVersionUID = 1L;

    @Override
    public int doStartTag() throws JspException {

        String rootPath = null;

        List<Object> mainList = null;

        List<Object> secondLevelList = null;

        List<Object> thirdLevelList = null;

        TopNavigationModel mainPageBean = null;

        TopNavigationModel secondLevelBean = null;

        TopNavigationModel thirdLevelBean = null;

        Resource rootResource = null;

        Iterator<Page> secondLevelChildren;

        Iterator<Page> thirdLevelChildren;

        Iterator<Page> mainPageChildrens;

        Page rootPage = null;
        Node jcrNode = null;
        Node pageNode = null;
        
        TopNavigationModel topNavigationBean = new TopNavigationModel();
        try {
        
            if (currentNode.hasProperty("parentPage")) {
                rootPath = currentNode.getProperty("parentPage").getValue().getString();

            }
            rootResource = slingRequest.getResourceResolver().getResource(rootPath);
            rootPage = rootResource == null ? null : rootResource.adaptTo(Page.class);
            if (rootPage != null) {
                mainPageChildrens = rootPage.listChildren();
                mainList = new ArrayList<>();
                while (mainPageChildrens.hasNext()) {
                    Page mainPage = mainPageChildrens.next();
                    mainPageBean = new TopNavigationModel();
                    mainPageBean.setTitle(mainPage.getTitle());
                    mainPageBean.setPath(mainPage.getPath() + Constants.HTML);
                    pageNode = mainPage.adaptTo(Node.class);

                    if (pageNode.hasNode(Constants.JCRCONTENT)) {
                        jcrNode = pageNode.getNode(Constants.JCRCONTENT);
                        if (!jcrNode.hasProperty(Constants.HIDEINNAV)) {
                            mainList.add(mainPageBean);
                        }
                    }
                    if (mainPage.listChildren() != null) {
                        secondLevelChildren = mainPage.listChildren();
                        secondLevelList = new ArrayList<>();
                        while (secondLevelChildren.hasNext()) {
                            Page secondPage = (Page) secondLevelChildren.next();
                            secondLevelBean = new TopNavigationModel();
                            secondLevelBean.setTitle(secondPage.getTitle());
                            secondLevelBean.setPath(secondPage.getPath() + Constants.HTML);
                            pageNode = secondPage.adaptTo(Node.class);
                            if (pageNode.hasNode(Constants.JCRCONTENT)) {
                                jcrNode = pageNode.getNode(Constants.JCRCONTENT);
                                if (!jcrNode.hasProperty(Constants.HIDEINNAV)) {
                                    secondLevelList.add(secondLevelBean);
                                }
                            }
                            if (secondPage.listChildren() != null) {
                                thirdLevelList = new ArrayList<>();
                                thirdLevelChildren = secondPage.listChildren();
                                while (thirdLevelChildren.hasNext()) {
                                    thirdLevelBean = new TopNavigationModel();
                                    Page thirdPage = thirdLevelChildren.next();
                                    thirdLevelBean.setTitle(thirdPage.getTitle());
                                    thirdLevelBean.setPath(thirdPage.getPath() + Constants.HTML);
                                    pageNode = thirdPage.adaptTo(Node.class);
                                    if (pageNode.hasNode(Constants.JCRCONTENT)) {
                                        jcrNode = pageNode.getNode(Constants.JCRCONTENT);
                                        if (!jcrNode.hasProperty(Constants.HIDEINNAV)) {
                                            thirdLevelList.add(thirdLevelBean);
                                        }
                                    }
                                }
                                secondLevelBean.setListOfTabs(thirdLevelList);
                            }
                        }
                        mainPageBean.setListOfTabs(secondLevelList);
                    }
                }
                topNavigationBean.setListOfTabs(mainList);
            }
        pageContext.setAttribute("topNavigationBean", topNavigationBean);
        
        } catch (RepositoryException | IllegalStateException e) {
        }

        return SKIP_BODY ;
    }
}
