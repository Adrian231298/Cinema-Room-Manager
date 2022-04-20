import java.util.Scanner;

class Main {
  private static int Tickets_Sold = 0;
  private static int Total_Income;
  private static int Current_Income = 0;
  private  static int TotalSeats;
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    print_some_text("Enter the number of rows:");
    int rows = scanner.nextInt();
    print_some_text("Enter the number of seats in each row:");
    int seats_in_each_row = scanner.nextInt();
    Total_Income = calculate_total_income(rows,seats_in_each_row);
    TotalSeats = rows * seats_in_each_row;
    System.out.println();
    cinema_loop(scanner, rows, seats_in_each_row);
  }

  public static void cinema_loop (Scanner scanner,
                                 int rows,
                                 int seats_in_each_row
                                 ) {
    boolean isDone = false;
    int ticket_row_number = 0;
    int ticket_seat_number = 0;
    String[][] table = create_the_table(rows, seats_in_each_row);
    while (!isDone) {
      menu();
      System.out.println();
      switch (scanner.nextInt())
        {
          case 1:
            {
              print_new_table(table, seats_in_each_row);
              System.out.println();
              break;
            }
          case 2:
            {
              boolean isSomething = false;
              while (!isSomething) {
                print_some_text("Enter a row number:");
                ticket_row_number = scanner.nextInt();
                print_some_text("Enter a seat number in that row:");
                ticket_seat_number = scanner.nextInt();
                if(ticket_row_number > rows || ticket_seat_number > seats_in_each_row) {
                  System.out.println("Wrong input!");
                } else {
                  if (table[ticket_row_number - 1][ticket_seat_number -1] != "B ")
                  {
                    Tickets_Sold += 1;
                   table[ticket_row_number - 1][ticket_seat_number - 1] = "B ";
                  int t_price = ticket_price(rows, seats_in_each_row, ticket_row_number);
                  System.out.println("Ticket price: $" + t_price);
                  Current_Income +=  t_price;
                    isSomething = true;
                  } else {
                    System.out.println("That ticket has already been purchased!");
                    System.out.println();
                  }
                }
                
              }
              System.out.println();
              break;
            }
          case 0:
            {
              isDone = true;
              break;
            }
          case 3:
            {
              statistics();
              break;
            }
          default:
            {
              break;
            }
        }
    }
  }
  public static void menu () {
    print_some_text("1. Show the seats");
    print_some_text("2. Buy a ticket");
    print_some_text("3. Statistics");
    print_some_text("0. Exit");
  }

  public static void print_some_text(String text) {
    System.out.println(text);
  } 

  public static void statistics() {
    float tr = (float)Tickets_Sold / TotalSeats;
    String fs = String.format("Percentage: %.2f",tr * 100);
    System.out.printf("Number of purchased tickets: %d\n", Tickets_Sold );
    System.out.println(fs + "%");
    System.out.printf("Current income: $%d\n", Current_Income);
    System.out.printf("Total Income: $%d", Total_Income);
    System.out.println();
  }

  public static String[][] create_the_table(int rows, int cols) {
    String[][] s = new String[rows][cols];
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            s[i][j] = "S ";
        }
    }
    return s;
  }
  public static int ticket_price(int rows,
                                 int seats,
                                 int row_location
                                 )
  {
    if (rows * seats <= 60) {
      return 10;
    } else {
      int half = rows / 2;
      int price = 0;
      if (row_location <= half) {
        price = 10;
      } else {
        price = 8;
      }
      return price;
    }
  }
  public static void print_new_table(String[][] table,
                                           int cols)
                                           
  {
    System.out.println("Cinema:");
    String co = " ";
    for (int i = 1; i <= cols; i++) {
      co += " " + i;
    }
    System.out.println(co);
    for (int i = 0; i < table.length; i++) {
        System.out.print(String.valueOf(i + 1) + " ");
      for (int j = 0; j < table[i].length; j++) {
        System.out.print(table[i][j]);
        }
      System.out.println();
    }
  }
  private static int calculate_total_income(int rows, int cols)
  {
    int internal_total_seats = rows * cols;
    int internal_total_income = 0;

    if (internal_total_seats <= 60) {
      internal_total_income = internal_total_seats * 10;
    } else {
      int half = rows / 2;
      int seats_in_half = half * cols; 
      for (int i = 1; i <= seats_in_half; i++) {
       internal_total_income += 10; 
      }
      int back_half = rows - half;
      int seats_in_back_half = back_half * cols;
      for (int j = 1; j <= seats_in_back_half; j++) {
       internal_total_income += 8; 
      }
    }
    return internal_total_income;
  }
}