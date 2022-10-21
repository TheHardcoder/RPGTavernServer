package de.byedev.rpgtavern.webapi;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.http.whiteboard.propertytypes.HttpWhiteboardResource;

@Component(service = WebpageResource.class)
@HttpWhiteboardResource(pattern = "/ui/*", prefix = "/")
public class WebpageResource {
}
