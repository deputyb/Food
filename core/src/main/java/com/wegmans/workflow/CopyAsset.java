package com.wegmans.workflow;

import javax.jcr.AccessDeniedException;
import javax.jcr.ItemNotFoundException;
import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.workflow.metadata.MetaDataMap;

@Component
@Service
public class CopyAsset extends AbstractWorkflowUtil {

    private static final Logger log = LoggerFactory.getLogger(CopyAsset.class);

    private String[] readArgument(MetaDataMap args) {
        String argument = args.get("PROCESS_ARGS", "false");
        return argument.split(";");
    }

    @Override
    protected void doExecute() {
        // TODO Auto-generated method stub
        super.doExecute();

        String[] arguments = readArgument(map);

        if (arguments.length > 1) {

            try {
                String destinationPath = payloadPath.replace(arguments[0], arguments[1]);
                String destinationFolder = currentNode.getParent().getPath().replace(arguments[0], "");

                if (!destinationFolder.isEmpty()) {
                    createBaseNode(arguments[1], destinationFolder);
                }

                if (!jcrSession.nodeExists(destinationPath)) {
                    jcrSession.getWorkspace().copy(payloadPath, destinationPath);
                }

            } catch (AccessDeniedException e1) {
                log.error(e1.getMessage());
            } catch (ItemNotFoundException e1) {
                log.error(e1.getMessage());
            } catch (RepositoryException e1) {
                log.error(e1.getMessage());
            } catch (Exception e) {
                log.error(e.getMessage());
            }

        }
    }

    private void createBaseNode(String rootpath, String destinationPath) throws Exception {
        if (!destinationPath.isEmpty()) {
            if (!jcrSession.nodeExists(destinationPath)) {
                Node node = jcrSession.getNode(rootpath);
                String[] basePathElements = destinationPath.split("/");

                for (String pathElement : basePathElements) {
                    if (!pathElement.isEmpty()) {
                        if (node.hasNode(pathElement)) {
                            node = node.getNode(pathElement);
                        } else {
                            node = node.addNode(pathElement, "sling:OrderedFolder");
                            jcrSession.save();
                        }
                    }
                }
                log.debug("created new dam path at {}", node);
            }
        }
    }

}
