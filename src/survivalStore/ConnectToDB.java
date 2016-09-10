//import java.sql.Connection;
//import java.sql.DriverManager;
//
//class ConnectToDB {
//	
//       public static void makeConnection (String[] args)
//       {
//           Connection conn = null;
//
//           try
//           {
//               String userName = "testuser";
//               String password = "testpass";
//               String url = "jdbc:mysql://localhost/test";
//               Class.forName ("com.mysql.jdbc.Driver").newInstance ();
//               conn = DriverManager.getConnection (url, userName, password);
//               System.out.println ("Database connection established");
//           }
//           catch (Exception e)
//           {
//               System.err.println ("Cannot connect to database server");
//           }
//           finally
//           {
//               if (conn != null)
//               {
//                   try
//                   {
//                       conn.close ();
//                       System.out.println ("Database connection terminated");
//                   }
//                   catch (Exception e) { /* ignore close errors */ }
//               }
//           }
//       }
//   }