package com.wegmans.workflow;

import java.util.Set;
import javax.jcr.Node;
import javax.jcr.Session;
import org.apache.sling.settings.SlingSettingsService;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import com.day.cq.workflow.WorkflowException;
import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.WorkItem;
import com.day.cq.workflow.exec.WorkflowData;
import com.day.cq.workflow.exec.WorkflowProcess;
import com.day.cq.workflow.metadata.MetaDataMap;
import com.wegmans.components.enums.RunMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractWorkflowUtil implements WorkflowProcess {

    private static final Logger log = LoggerFactory.getLogger(CopyAsset.class);

    protected final static String JCR_PATH = "JCR_PATH";
    protected final static String JCR_UUID = "JCR_UUID";

    protected Session jcrSession = null;
    protected String resourcePath = "";
    protected String payloadPath = "";
    protected MetaDataMap map = null;
    protected WorkItem workItem = null;
    protected WorkflowSession session = null;
    protected Node currentNode = null;

    @Override
    public void execute(WorkItem workItem, WorkflowSession session, MetaDataMap map) throws WorkflowException {
        try {
            this.map = map;
            this.session = session;
            this.workItem = workItem;
            payloadPath = workItem.getWorkflowData().getPayload().toString();
            jcrSession = session.getSession();
            currentNode = jcrSession.getNode(payloadPath);
            WorkflowData workflowData = workItem.getWorkflowData();
            if (workflowData.getPayloadType().equals(JCR_PATH)) {
                doExecute();
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    protected void doExecute() {

    }

    public static Set<String> getRunModes() {
        BundleContext bundleContext = FrameworkUtil.getBundle(AbstractWorkflowUtil.class).getBundleContext();
        ServiceReference serviceReference = bundleContext.getServiceReference(SlingSettingsService.class.getName());
        SlingSettingsService slingSettingsService = (SlingSettingsService) bundleContext.getService(serviceReference);
        return slingSettingsService.getRunModes();
    }

    protected boolean isAuthorRunMode() {

        Set<String> runmodes = getRunModes();

        if (runmodes != null && runmodes.contains(RunMode.AUTHOR.getId())) {
            return true;
        }
        return false;
    }
}
