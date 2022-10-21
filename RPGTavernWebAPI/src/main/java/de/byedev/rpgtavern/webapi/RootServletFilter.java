package de.byedev.rpgtavern.webapi;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.log.Logger;
import org.osgi.service.log.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component(
        property = "osgi.http.whiteboard.filter.pattern=/",
        scope= ServiceScope.PROTOTYPE)
public class RootServletFilter implements Filter {

    @Reference(service= LoggerFactory.class)
    private Logger logger;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("fitlering start");
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = ((HttpServletRequest)request);
            String requestURI = httpServletRequest.getRequestURI();
            String contextPath = httpServletRequest.getContextPath();
            logger.info("fitlering " + contextPath);
            if (!contextPath.startsWith("/ui") && (contextPath.endsWith(".html") || contextPath.endsWith(".js") || contextPath.endsWith(".css") || contextPath.endsWith(".ico"))){
                logger.info("Forwarding " + contextPath + " to /ui" + contextPath);
                httpServletRequest.getServletContext().getRequestDispatcher(
                        "/ui"+contextPath).forward(request,response);

            }
            if (contextPath.trim().replace("/","").isEmpty()){
                httpServletRequest.getServletContext().getRequestDispatcher(
                        "/ui/index.html").forward(request,response);

            }

        }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
    }
}
