using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class _Default : System.Web.UI.Page
{
    static int acount = 0;
    int acount2=0;
    static int Enx = 0, Eny = 0;
    static int Exx = 0, Exy = 0;
    
    string flag="f";
    static int[,] a = new int[,]
        {
            {7,1,1,1,0,1,0,0,0,0,0,1,0,0},
            {0,1,1,1,0,1,1,1,1,1,0,0,0,0},
            {0,1,1,0,0,0,0,0,0,1,0,0,0,0},
            {0,1,1,1,1,1,1,1,1,1,1,1,0,0},
            {0,1,0,0,0,1,0,0,0,0,0,1,0,0},
            {0,1,0,0,0,1,0,0,0,0,0,1,0,0},
            {0,1,0,0,0,1,0,0,0,0,0,1,0,0},
            {0,1,1,1,1,1,1,1,1,1,0,1,1,8},
            {0,1,0,0,0,1,0,0,0,1,1,1,1,0},
            {0,1,0,0,0,1,1,1,1,1,0,0,0,0}
        };
    
    int height = a.GetLength(0);
    int width = a.GetLength(1);
    string[] stcn;
    
    int[,] b = new int[a.GetLength(0),a.GetLength(1) ];
    int lastcheckx;
    int lastchecky;
    protected void Page_Load(object sender, EventArgs e)
    {
        stcn = new string [5000];
        int xxx = a.GetUpperBound(0);
        for (int i = 0; i < a.GetUpperBound(0) + 1; i++)
        {
            if (a[i, 0] == 7)
            {
                Enx = i;
                Eny = 0;
              
            }
            if (a[i, width-1] == 8) 
            {
                Exx = i;
                Exy = width-1;
               
            }
        }
    
        
     
    Right:
        if (flag != "l" && flag != "e" && flag != "f" )
        {

            flag = "r";
           // acount++;
            //stcn[acount] = "r";
            if (checkRight()==1)
            {
                goto Right;
            }
            else if (checkRight() == 8)
            {
                for (int i = 0; i < height; i++)
                {
                    for (int j = 0; j < width; j++)
                    {
                        Response.Write(a[i, j]);
                    }
                    Response.Write("<br/>");
                    flag = "e";
                    
                }
                for (int i = 0; i < height; i++)
                {

                    b[Enx, Eny] = 7;
                    b[Exx, Exy] = 8;
                    for (int j = 0; j < width; j++)
                    {
                        Response.Write(b[i, j]);
                    }
                    Response.Write("<br/>");
                    flag = "e";
                }
             
            }
            else if (checkUp()==1)
            {
                goto Up;
            }
            else if (checkDown()==1)
            {
                goto Down;
            }
            else
            {
                a[lastcheckx, lastchecky] = 0;
                goto Start;
            }

            
        }
    Up:
    if (flag != "d" && flag != "e" && flag != "f")
        {
            flag = "u";
          //  acount++;
           // stcn[acount] = "u";

            if (checkUp()==1)
            {
                goto Up;
            }
            else if (checkRight() == 1)
            {
                goto Right;
            }
            else if (checkRight() == 8)
            {
                for (int i = 0; i < height; i++)
                {
                    for (int j = 0; j < width; j++)
                    {
                        Response.Write(a[i, j]);
                    }
                    Response.Write("<br/>");
                    flag = "e";
                }
                for (int i = 0; i < height; i++)
                {
                    for (int j = 0; j < width; j++)
                    {
                        Response.Write(b[i, j]);
                    }
                    Response.Write("<br/>");
                    flag = "e";
                }

            }
            else if (checkLeft()==1)
            {
                goto Left;
            }
            else
            {
                a[lastcheckx, lastchecky] = 0;
                goto Start;
            }
            
        }
     Down:
    if (flag != "u" && flag != "e" && flag != "f")
        {
            flag = "d";
           // acount++;
            //stcn[acount] = "d";
            if (checkDown()==1)
            {
                goto Down;
            }
            else if (checkRight()==1)
            {
                goto Right;
            }
            else if (checkRight() == 8)
            {
                for (int i = 0; i < height; i++)
                {
                    for (int j = 0; j < width; j++)
                    {
                        Response.Write(a[i, j]);
                    }
                    Response.Write("<br/>");
                    flag = "e";
                }
                for (int i = 0; i < height; i++)
                {
                    for (int j = 0; j < width; j++)
                    {
                        Response.Write(b[i, j]);
                    }
                    Response.Write("<br/>");
                    flag = "e";
                }

            }
            else if (checkLeft()==1)
            {
                goto Left;
            }
            
            else
            {
                a[lastcheckx, lastchecky] = 0;
                goto Start;
            }
            
        }
     Left:
     if (flag != "r" && flag != "e" && flag != "f")
        {
            flag = "l";
            //acount++;
            //stcn[acount] = "l";
            if (checkUp()==1)
            {
                goto Up;
            }
            else if (checkDown() == 1)
            {
                goto Down;
            }
            else if (checkLeft()==1)
            {
                goto Left;
            }
            else
            {
                a[lastcheckx, lastchecky] = 0;
                goto Start;
            }
            
        }
        else
        { 
            lastcheckx=9;
            lastchecky = 9;
        }

    Start:
     if (flag != "e")
     {
         stcn[acount] = "s";
         acount++;
         stcn[acount] = "s";
         acount++;
         stcn[acount] = "s";
         acount++;
         stcn[acount] = "s";
         lastcheckx = Enx;
         lastchecky = Eny;
         flag = "a";
         Array.Clear(b, 0, b.Length);
         if (checkRight() == 0)
         {
             Response.Write("No Find");

         }
         else
         {
             flag = "s";
             goto Right;
         }
     }
       
   } 
        public int checkLeft()
        {
            if (lastchecky - 1 > 0)
            {

                if (b[lastcheckx, lastchecky - 1] != 3)
                {
                    acount2=acount-1;
                    if (a[lastcheckx, lastchecky - 1] == 1 && stcn[acount2]!="r")
                    {
                        acount++;
                        stcn[acount] = "l";
                        lastchecky--;
                        b[lastcheckx, lastchecky] = 3;
                        return 1;

                    }
                    else { return 0; }
                }
                else { return 0; }

            }
            else
            {
                return 0;
            }
           
            
        }
        public int checkRight()
        {
            
                if (lastchecky + 1 < width)
                {
                    if (b[lastcheckx, lastchecky + 1] != 3)
                    {

                        acount2 = acount - 1;
                        if (a[lastcheckx, lastchecky + 1] == 1 && stcn[acount2]!="l")
                        {
                            acount++;
                            stcn[acount] = "r";
                            lastchecky++;
                            b[lastcheckx, lastchecky] = 3;
                            return 1;

                        }
                        else if (a[lastcheckx, lastchecky + 1] == 8)
                        {
                            return 8;
                        }
                        else { return 0; }
                }
                else { return 0; }
        
            }
            else
            {
                return 0;
            }
        
        }
        public int checkUp()
        {

            if (lastcheckx - 1 >= 0)
            {
                if (b[lastcheckx - 1, lastchecky] != 3)
                {
                    acount2 = acount - 1;
                    if (a[lastcheckx - 1, lastchecky] == 1 && stcn[acount2]!="d")
                    {
                        acount++;
                        stcn[acount] = "u";
                        lastcheckx--;
                        b[lastcheckx, lastchecky] = 3;
                        return 1;
                    }
                    else { return 0; }
                }
                else { return 0; }
            }
            else
            {
                return 0;
            }
        }
        public int checkDown()
        {
            
                if (lastcheckx + 1 < height)
                {
                    if (b[lastcheckx + 1, lastchecky] != 3)
                    {
                        acount2 = acount - 1;
                        if (a[lastcheckx + 1, lastchecky] == 1 && stcn[acount2]!="u")
                        {
                            acount++;
                            stcn[acount] = "d";
                            lastcheckx++;
                            b[lastcheckx, lastchecky] = 3;
                            return 1;
                        }
                        else { return 0; }
                    }
                    else { return 0; }
            
            }
            else
            {
                return 0;
            }
        }
    
}
