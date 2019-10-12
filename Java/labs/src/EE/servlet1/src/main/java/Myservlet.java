
import java.awt.Canvas;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

//Сервлет формирующий графический рисунок
@WebServlet("/hello2.png")
public class Myservlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /* resp.setContentType("text/html");
        
        PrintWriter writer = resp.getWriter();
        try {
            writer.println("<h2>my servlet</h2>");
        } finally {
            writer.close();  
        }
      */
        resp.setContentType("image/png"); //тип отклика сервлета
        OutputStream out = resp.getOutputStream();
        //Рисуем изображение 
        BufferedImage img = new BufferedImage(640, 120, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = (Graphics2D) img.createGraphics();
        g2d.setFont(new Font("Arial", Font.BOLD, 72));
        Random random = new Random();
        g2d.setColor(new Color((random.nextInt(256)),random.nextInt(256),random.nextInt(256)));
        g2d.drawString("Hello",100,100);
        g2d.drawImage(img, null, 100, 100);  
        File e = new File("ff");
        ImageIO.write(img, "png", out); //png - строка определяющая формат
        out.flush();
        out.close();
}
}
        
    
    

