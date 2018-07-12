import java.util.*;
import java.lang.*;
public class CircleSplitter
{
  public static void main(String [] args)
  {
    ArrayList<Point> data = get_data();
    int half=data.size()/2;
    ArrayList<Circle> qualified = new ArrayList<Circle>();
    
    float step_size = (float)0.005;
    float min_radius = (float)1.0;
    for(float x=0;x<1.0;x+=step_size)
    {
      for(float y=0;y<1.0;y+=step_size)
      {
        for(float r=0;r<1.0;r+=step_size)
        {
          Circle circle = new Circle(x,y,r);
          if(Circle.points_in_circle(circle, data) == half && Circle.is_in_square(circle) && circle.radius<min_radius)
          {
            qualified.add(circle);
            min_radius=circle.radius;
            break;
          }
         
        }
      }
    }
    print_circles(qualified);
  }
  
  private static ArrayList<Point> get_data()
  {
    ArrayList<Point> out = new ArrayList<Point>();
    Scanner scan = new Scanner(System.in);
    
    int count = scan.nextInt();
    for(int x=0;x<count;x++)
    {
      out.add(new Point(scan.nextFloat(), scan.nextFloat()));
      
    }
    
    return out;
  }

  
  private static void print_data(ArrayList<Point> input)
  {
    for(int x=0;x<input.size();x++)
    {
      System.out.println("x: " + input.get(x).x + " y: " + input.get(x).y);
    }
  }
  
  private static void print_circles(ArrayList<Circle> input)
  {
    for(int x=0;x<input.size();x++)
    {
      System.out.printf("Midpoint: (%.3f,%.3f) Radius: %.3f \n", input.get(x).middle.x,input.get(x).middle.y,input.get(x).radius);
      //System.out.println("Midpoint: (" + input.get(x).middle.x + "," + input.get(x).middle.y + ") Radius: " + input.get(x).radius);
    }
  }
}

class Point
{
  public float x;
  public float y;
  
  public Point(float x, float y)
  {
    this.x=x;
    this.y=y;
  }
  
  public static float distance_between(Point one, Point two)
  {
    return (float)Math.abs(Math.sqrt(Math.pow(one.x-two.x,2) + Math.pow(one.y-two.y,2)));
  }
}

class Circle
{
  public Point middle;
  public float radius;
  
  public Circle(float x, float y, float radius)
  {
    middle = new Point(x,y);
    this.radius=radius;
  }
  
  public static boolean is_in_square(Circle circle)
  {
    return (circle.middle.x + circle.radius <= 1.0) && (circle.middle.x - circle.radius >= 0.0) && (circle.middle.y + circle.radius <= 1.0) && (circle.middle.y - circle.radius>= 0.0);
  }
  
  public static int points_in_circle(Circle circle, ArrayList<Point> input)
  {
    int count=0;
    for(int x=0;x<input.size();x++)
    {
      if(Point.distance_between(circle.middle, input.get(x)) <= circle.radius)
      {
        count++;
      }
    }
    
    return count;
  }
}