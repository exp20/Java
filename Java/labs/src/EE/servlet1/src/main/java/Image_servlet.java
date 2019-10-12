
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Image_servlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /* resp.setContentType("image/png");
        File file = new File("../../../resources/hello.png");
        BufferedImage image = ImageIO.read(file);
        ImageIO.write(image, "PNG", resp.getOutputStream());
      */
        PrintWriter writer = resp.getWriter();
        try {
            writer.println("<h2>my servlet</h2>");
        } finally {
            writer.close();  
        }
        super.doGet(req, resp);
    }
  
}
