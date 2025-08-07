package MusicPlayer;
import DataStructure.Deque1;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

class MyException extends RuntimeException{
    MyException(String exp)
    {
        super(exp);
    }
}
class Main1{
    static Scanner sc=new Scanner(System.in);
    static Connection con;
    public static void main(String[] args) throws ClassNotFoundException, SQLException 
    {
            Main1 main1=new Main1();
            main1.Database();
            main1.Homepage();
            System.out.println();
            int choice=0;

            do{
                try{
                    Colour.ChangeColour("w");
                    System.out.println("--------------------------------------------------------------");
                    Colour.ChangeColour("cr");
                    Colour.ChangeColour("g");
                    System.out.print("\n Do you want to : \n \n 1.Play song online \n 2.Make your own playlist \n 3.Update your playlist \n 4.Play your playlist song \n 5.Exit \n Enter your choice: ");
                    Colour.ChangeColour("cr");
                    choice=sc.nextInt();
                    sc.nextLine();
                    PlaylistManager playlistManager=new PlaylistManager(con);

                    switch(choice)
                    {
                        case 1:
                        playlistManager.addSong1();
                        playlistManager.OnlineSong();
                        break;

                        case 2:
                        playlistManager.MakeYourOwnPlaylist();
                        break;

                        case 3:
                        playlistManager.UpdatePlaylist();
                        break;

                        case 4:
                        main1.FillUserPlaylistSong();
                        playlistManager.addUserPlaylistSong();
                        playlistManager.PlayYourOwnPlaylist();
                        break;

                        case 5:
                        Colour.ChangeColour("y");
                        System.out.println("\n Exiting Music Player...");
                        Colour.ChangeColour("cr");
                        break;

                        default:
                        Colour.ChangeColour("r");
                        System.out.println(" Enter your choice between 1 to 4");
                        Colour.ChangeColour("cr");
                        break;
                    }

                    }
                    catch(Exception e)
                    {
                        Colour.ChangeColour("r");
                        System.out.println("\n Invalid Input Entered");
                        Colour.ChangeColour("cr");
                        sc.nextLine();
                    }

                }while(choice!=5);
        }

    void Database() throws SQLException, ClassNotFoundException
    {
       try{
        String url = "jdbc:mysql://localhost:3306/music_player";
        String user = "root";
        String dbpassword = "";
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, dbpassword);
        FillOnlineSong();
       }
       catch(Exception e)
       {
        System.out.println("Database is not connected properly");
        sc.nextLine();
       }
    }

    void Homepage()
    {
        Colour.ChangeColour("bk,gb");
        System.out.println();
        System.out.println("\t\t\t----------Welcome to Music Player----------");
        Colour.ChangeColour("cr");
        boolean b=true;
        User1 user=new User1(con);
        int choice;
        do{
            try{
                Colour.ChangeColour("g");
                System.out.print("\n Press 1: Login \n Press 2: Sign-Up");
                System.out.print("\n Enter your choice: ");
                Colour.ChangeColour("cr");
                choice=sc.nextInt();
                sc.nextLine();
                if(choice==1)
                {
                    b=user.CheckUser();
                }
                else if(choice==2)
                {
                    b=!user.SignUp();
                }
                else {
                    System.out.println(" Enter 1 or 2");
                }
            }
            catch(Exception e)
            {
                Colour.ChangeColour("r");
                System.out.println("\n Invalid Input Entered");
                Colour.ChangeColour("cr");
                sc.nextLine();
            }
        }while(b);
    }
    static ArrayDeque <String> fetchonlinesongpath=new ArrayDeque<>();
    static ArrayDeque <String> fetchonlinesongname=new ArrayDeque<>();
    static ArrayList<String> sidlist=new ArrayList<>();

        void FillOnlineSong() throws SQLException
        {
            String sql1="Select * from songdetails";
            PreparedStatement pst=con.prepareCall(sql1);
            ResultSet rs=pst.executeQuery();
            sidlist.clear();
            fetchonlinesongname.clear();
            while(rs.next())
            {
                String songId=rs.getString(1);
                String SongName=rs.getString(2);
                sidlist.add(songId);
                fetchonlinesongname.addLast(SongName);
            }
        }

        
        String userId;
        String SongName;
        String SongPath;

        static ArrayDeque <String> fetchUserSongPath=new ArrayDeque<>();
        static ArrayDeque <String> fetchUserSong=new ArrayDeque<>();

        void FillUserPlaylistSong() throws SQLException
        {
            String sql2="Select * from playlistdetails where UserID=?";
            PreparedStatement pst=con.prepareCall(sql2);
            pst.setString(1, User1.userId);
            ResultSet rs=pst.executeQuery();
            fetchUserSong.clear();
            fetchUserSongPath.clear();
            while(rs.next())
            {
                userId=rs.getString("UserID");
                SongName=rs.getString("SongName");
                SongPath=rs.getString("SongPath");

                fetchUserSongPath.addLast(SongPath);
                fetchUserSong.addLast(SongName);
            }
        }
}

class User1
{
    Connection con;
    Scanner sc=new Scanner(System.in);
    User1(Connection con)
    {
        this.con=con;
    }

        String name;
        LocalDate DateOfBirth;
        static String userId;
        String mobileno;
        String password;

    boolean CheckUser() throws SQLException, InterruptedException {
        String userID;
        String Password;
        Hashtable<String, String> ht = new Hashtable<>();
        Colour.ChangeColour("g");
        System.out.print("\n Enter your UserId: ");
        Colour.ChangeColour("cr");
        userId = sc.nextLine();
        Colour.ChangeColour("g");
        System.out.print(" Enter your password: ");
        Colour.ChangeColour("cr");
        String Password1 = sc.nextLine();
        boolean b = true;
        Statement st = con.createStatement();
        String sql = "Select UserID, Password from user1";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            userID = rs.getString("UserId");
            Password = rs.getString("Password");
            ht.put(userID, Password);
        }
        if (!ht.containsKey(userId)) {
            Colour.ChangeColour("r");
            System.out.print("\n UserId does not exist,Please sign up");
            System.out.println();
            Colour.ChangeColour("cr");
        } else if (!ht.get(userId).equals(Password1)) {
            Colour.ChangeColour("r");
            System.out.print("\n Entered Password does not match");
            System.out.println();
            Colour.ChangeColour("cr");
        } else {
            Colour.ChangeColour("g");
            System.out.println("\n Loading...... ");
            Colour.ChangeColour("cr");
            Thread.currentThread().sleep(2000);
            Colour.ChangeColour("b,i");
            System.out.print("\n Welcome back " + userId);
            System.out.println();
            Colour.ChangeColour("cr");
            b = false;
        }
        return b;
    }

    boolean SignUp() throws SQLException, InterruptedException
    {
        String sql="Insert into user1(UserID,Name,MobileNo,DateOfBirth,Password) values(?,?,?,?,?)"; 
        PreparedStatement pst=con.prepareStatement(sql);

        Verify1 verify=new Verify1();
        name=verify.name();
        mobileno=verify.mobileNo();
        int i=0;
        do {
            try {
                String d[] = verify.dateOfBirth().split("-");
                DateOfBirth = LocalDate.of(Integer.parseInt(d[0]), Integer.parseInt(d[1]), Integer.parseInt(d[2]));
                i++;
            } catch (Exception e) {
                Colour.ChangeColour("r");
                System.out.println("\n Enter DOB properly");
                Colour.ChangeColour("cr");
            }
            
        } while (i==0);
        userId = name.substring(0, 4) + DateOfBirth.getDayOfMonth();
        Colour.ChangeColour("b");
        System.out.print("\n Your User ID is ");
        Colour.ChangeColour("cr");
        System.out.println(userId);
        System.out.println();
        password = verify.password();

        pst.setString(1, userId);
        pst.setString(2, name);
        pst.setString(3, "+91 " + mobileno);
        pst.setDate(4, java.sql.Date.valueOf(DateOfBirth));
        pst.setString(5, password);

        int r = pst.executeUpdate();
        System.out.println();
        return r>0;
    }

}

class Verify1{
    Scanner sc = new Scanner(System.in);
    String Name;
    String Mono;
    String DateOfBirth;
    String Password;

    String name(){
        boolean b=true;
        while(b){
            try{
                Colour.ChangeColour("g");
                System.out.print("\n Enter name: \n (First name   Middle name   Last name) \n Name : ");
                Colour.ChangeColour("cr");
                Name=sc.nextLine().trim();
                if(Name.isEmpty()){
                    Colour.ChangeColour("r");
                    System.out.println("\n It is mandatory to enter name");
                    Colour.ChangeColour("cr");
                }
                else if(Name.length()<=3)
                {
                    Colour.ChangeColour("r");
                    System.out.println("\n Enter your full name");
                    Colour.ChangeColour("cr");
                }
                else{
                    b=false;
                }
            }
            catch(Exception e)
            {
                Colour.ChangeColour("r");
                System.out.println("\n Enter name properly");
                Colour.ChangeColour("cr");
                sc.nextLine();
            }
        }
        return Name;
    }

    String mobileNo() {
        boolean b = true;
        while (b) {
            try {
                Colour.ChangeColour("g");
                System.out.print("\n Enter Mobile no : ");
                Colour.ChangeColour("cr");
                Mono = sc.next().trim();
                sc.nextLine();
                if (Mono.length() == 10 && (Mono.charAt(0) == '9' || Mono.charAt(0) == '8' || Mono.charAt(0) == '7')) {
                    int count = 0;
                    for (int i = 0; i < 10; i++) {
                        if (Mono.charAt(i) >= '0' && Mono.charAt(i) <= '9') {
                            count++;
                        }
                        }
                        if(count==10)
                        {
                            b=false;
                        }
                        else{
                            Colour.ChangeColour("r");
                            System.out.println("\n Enter digits only");
                            Colour.ChangeColour("cr");
                    }
                }
                  else {
                    Colour.ChangeColour("r");
                    System.out.println("\n Enter a valid 10-digit mobile number starting with 9, 8, or 7.");
                    Colour.ChangeColour("cr");
                }
            } catch (Exception e) {
                Colour.ChangeColour("r");
                System.out.println("\n Enter Mobile No properly");
                Colour.ChangeColour("cr");
                sc.nextLine();
            }
        }
        return Mono;
    }

    String dateOfBirth() {
        boolean b = true;
            while (b) {
            try {
                Colour.ChangeColour("g");
                System.out.print("\n Enter DateOfBirth \n (YYYY-MM-DD) \n DOB : ");
                Colour.ChangeColour("cr");
                DateOfBirth = sc.nextLine().trim();
                if (DateOfBirth.isEmpty()) {
                    Colour.ChangeColour("r");
                    System.out.println("DateOfBirth cannot be empty");
                    Colour.ChangeColour("cr");
                } else {
                    b = false;
                }
            } catch (Exception e) {
                Colour.ChangeColour("r");
                System.out.println("\n Enter DateOfBirth properly");
                Colour.ChangeColour("cr");
                sc.nextLine();
            }
        }
            return DateOfBirth;
    }

    String password() {
        boolean b = true;
        while (b) {
            try {
                Colour.ChangeColour("g");
                System.out.print("\n Create Password: ");
                System.out.print("\n (Password must be of length 4)");
                System.out.print("\n Enter Password : ");
                Colour.ChangeColour("cr");
                Password = sc.next().trim();
                sc.nextLine();
                if (Password.isEmpty()) {
                    Colour.ChangeColour("r");
                    System.out.println("\n Password cannot be empty.");
                    Colour.ChangeColour("cr");
                } else if (Password.length() != 4) {
                    Colour.ChangeColour("r");
                    System.out.println("\n Length must be 4");
                    Colour.ChangeColour("cr");
                } else {
                    Colour.ChangeColour("g");
                    System.out.print("\n Confirm Password: ");
                    Colour.ChangeColour("cr");
                    String temp = sc.nextLine().trim();
                    if (Password.equals(temp)) {
                        b = false;
                    } else {
                        Colour.ChangeColour("r");
                        System.out.println("\n It does not match your Password.");
                        Colour.ChangeColour("cr");
                    }
                }
            } catch (Exception e) {
                Colour.ChangeColour("r");
                System.out.println("\n Enter Password properly");
                Colour.ChangeColour("cr");
                sc.nextLine();
            }
        }
        return Password;
    }
}

class PlaylistManager {
    Main1 main1=new Main1(); 
    Connection con;
    PlaylistManager(Connection con)
    {
        this.con=con;
    }
    Scanner sc = new Scanner(System.in);

    Deque1 onlineplaylist=new Deque1(40);  // Deque1 class object

    void addSong1() throws SQLException
    {
        String sql="Select SongPath from songdetails";
        PreparedStatement pst=con.prepareStatement(sql);
        ResultSet rs=pst.executeQuery();
        while(rs.next())
        {
            onlineplaylist.InsertLast(rs.getString(1));
        }
    }

    
    int choice;
    
    void OnlineSong() throws SQLException {
        do {
            try {
                System.out.print("\n --------------------------------------------------------------");
                System.out.println();
                System.out.print("\n ");
                Colour.ChangeColour("bk,gb");
                System.out.print("Playing song online : ");
                Colour.ChangeColour("cr");
                System.out.println();
                Colour.ChangeColour("g");
                System.out.print("\n 1.Play next song \n 2.Play previous song \n 3.Shuffle the song \n 4.Display online playlist  \n 5.Exit ");
                System.out.print("\n Enter your choice : ");
                Colour.ChangeColour("cr");
                choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        System.out.println();
                        PlayNextSongOnline();
                        break;
    
                    case 2:
                        System.out.println();
                        PlayPreviousSongOnline();
                        break;
    
                    case 3:
                        System.out.println();
                        ShuffleOnlinePlaylist();
                        break;
    
                    case 4:
                        DisplayOnlinePlaylist();
                        break;
    
                    case 5:
                        Colour.ChangeColour("y");
                        System.out.println("\n Exiting the online playlist...");
                        System.out.println();
                        Colour.ChangeColour("cr");
                        break;
    
                    default:
                        System.out.println();
                        Colour.ChangeColour("r");
                        System.out.print("\n Enter choice between 1 to 4 ");
                        Colour.ChangeColour("cr");
                        break;
                }
            } catch (Exception e) {
                Colour.ChangeColour("r");
                System.out.println("\n Invalid Input entered");
                Colour.ChangeColour("cr");
                sc.nextLine();
            }
        } while (choice != 5);
    }
    

    void PlayNextSongOnline() {
        if (!onlineplaylist.IsEmpty()) {
            String nextSong = onlineplaylist.DeleteFirst();
            PlaySong(nextSong);
            onlineplaylist.InsertLast(nextSong);
        } else {
            Colour.ChangeColour("r");
            System.out.println("\n Playlist is empty.");
            Colour.ChangeColour("cr");
        }
    }

    void PlayPreviousSongOnline() {
        if (!onlineplaylist.IsEmpty()) {
            String previousSong = onlineplaylist.DeleteLast();
            PlaySong(previousSong);
            onlineplaylist.InsertFirst(previousSong);
        } else {
            Colour.ChangeColour("r");
            System.out.println("\n Playlist is empty.");
            Colour.ChangeColour("cr");
        }
    }

    void ShuffleOnlinePlaylist() {
        if (!onlineplaylist.IsEmpty()) {
            onlineplaylist.Shuffle();
            System.out.println("\n Playlist shuffled.");
        } else {
            Colour.ChangeColour("r");
            System.out.println("\n Playlist is empty.");
            Colour.ChangeColour("cr");
        }
    }

    void DisplayOnlinePlaylist() throws SQLException {
       try{
        String sql="{call DisplayOnlinePlaylistSong()}";
        CallableStatement cst=con.prepareCall(sql);
        ResultSet rs=cst.executeQuery(sql);
        Colour.ChangeColour("g");
        System.out.print("\n Displaying online playlist song : ");
        System.out.println();
        Colour.ChangeColour("cr");
        while(rs.next())
            {
                System.out.print("\n "+rs.getString("SongName"));
            }
        System.out.println();
        }
        catch(Exception e)
        {
            Colour.ChangeColour("r");
            System.out.println("Exception in procedure DisplayonlinePlaylistSong");
            Colour.ChangeColour("cr");
            sc.nextLine();
        }
    }



    // ArrayDeque <String> tempplaylist = new ArrayDeque<>(Main1.fetchonlinesongname);
    ArrayDeque <String> userplaylist = new ArrayDeque<>();

    void SearchSong(String inputSong) throws SQLException
    {
        main1.FillUserPlaylistSong();
        main1.FillOnlineSong();
        ArrayDeque <String> tempSongName=new ArrayDeque<>(Main1.fetchUserSong);
        ArrayList <String> searchSong=new ArrayList<>(Main1.fetchonlinesongname);

        if(searchSong.contains(inputSong)) 
        {
            if(!tempSongName.contains(inputSong))
            {
                String inputSongPath="D://Music/"+inputSong+".wav";
            userplaylist.addLast(inputSongPath);
            System.out.print("\n "+inputSong+" song added successfully in the playlist");
            
            String sql="Insert into playlistdetails (UserID,SongName,SongPath) values(?,?,?)";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1, User1.userId);
            pst.setString(2, inputSong);
            pst.setString(3, inputSongPath);
            pst.executeUpdate();
            System.out.println();
            }
            else{
                System.out.println();
                Colour.ChangeColour("y");
                System.out.println("\n Entered song is already present in your playlist.");
                Colour.ChangeColour("cr");
            }
        }
        else{
            System.out.println();
            Colour.ChangeColour("r");
            System.out.println("/n Entered Song does not exists");
            Colour.ChangeColour("cr");
        }
    }

    ArrayDeque <String> DeleteUserSong=new ArrayDeque<>();
    
    void deleteSong(String inputSong) throws SQLException
    {
        main1.FillUserPlaylistSong();
        ArrayList <String> deleteSong=new ArrayList<>(Main1.fetchUserSong);
        if(deleteSong.contains(inputSong)) 
        {   
            String sql="Delete from playlistdetails where UserID=? and SongName=? ";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1, User1.userId);
            pst.setString(2, inputSong);
            pst.executeUpdate();
            System.out.println("\n " +inputSong+" song deleted successfully from your playlist");
        }
        else{
            System.out.println("\n ");
            Colour.ChangeColour("y");
            System.out.print("Entered Song does not exists");
            Colour.ChangeColour("cr");
        }
    }

    void deleteFullPlaylist() throws SQLException
    {
        main1.FillUserPlaylistSong();
        String sql="Delete from playlistdetails where UserID=?";
        PreparedStatement pst=con.prepareStatement(sql);
        pst.setString(1, User1.userId);
        pst.executeUpdate();
        Colour.ChangeColour("y");
        System.out.println("\n Current playlist delete");
        Colour.ChangeColour("cr");

    }

    void MakeYourOwnPlaylist() throws SQLException
    {
        String inputSong;
        boolean b=true;
        while(b){
            try{
                System.out.println("--------------------------------------------------------------");
                System.out.print("\n ");
                Colour.ChangeColour("bk,gb");
                System.out.print("For making your own playlist: ");
                Colour.ChangeColour("cr");
                System.out.println();
                Colour.ChangeColour("g");
                System.out.print("\n 1.Deva Deva \n 2.Khalasi \n 3.Subhanallah \n 4.Woh Din \n 5.Namo Namo \n 6.Ilhali \n 7.Raabta \n 8.kaise Hua \n 9.Matargashti \n 10.Nadaan Parinde \n 11.Sajni Re \n 12.Naina Kya kasur \n 13.Kesariya \n 14.Hawayein \n 15.Kun faya kun \n 16.Exit ");
                System.out.print("\n Enter the song name you want to add in your playlist : ");
                Colour.ChangeColour("cr");
                inputSong=sc.nextLine();
    
                if(inputSong.equalsIgnoreCase("exit"))
                {
                    b=false;
                    return;
                }
    
                SearchSong(inputSong.toUpperCase());
                
            }
            catch(Exception e)
            {
                Colour.ChangeColour("r");
                System.out.println("\n Invalid Input Entered");
                Colour.ChangeColour("cr");
                sc.nextLine();
            }
        }
    }

    void UpdatePlaylist() {
        int choice = 0;
        do {
            try {
                System.out.println();
                System.out.println("--------------------------------------------------------------");
                Colour.ChangeColour("g");
                System.out.print(" Do you want to : \n 1.Add song \n 2.Delete song \n 3.Make a new playlist \n   (old playlist will be deleted) \n 4.Exit \n Enter your choice : ");
                Colour.ChangeColour("cr");
                choice = sc.nextInt();
                sc.nextLine();
                Main1 main1 = new Main1();
                switch (choice) {
                    case 1:
                        System.out.println();
                        Colour.ChangeColour("bk,gb");
                        System.out.println("Adding New Song To existing Playlist:");
                        Colour.ChangeColour("cr");
                        Colour.ChangeColour("g");
                        System.out.print("\n 1.Deva Deva \n 2.Khalasi \n 3.Subhanallah \n 4.Woh Din \n 5.Namo Namo \n 6.Ilhali \n 7.Raabta \n 8.kaise Hua \n 9.Matargashti \n 10.Nadaan Parinde \n 11.Sajni Re \n 12.Naina Kya kasur \n 13.Kesariya \n 14.Hawayein \n 15.Kun faya kun \n 16.Exit ");
                        System.out.print("\n Enter the song name you want to add in your existing playlist : ");
                        Colour.ChangeColour("cr");
                        String inputSong = sc.nextLine().toUpperCase();
                        SearchSong(inputSong);
                        main1.FillUserPlaylistSong();
                        break;
    
                    case 2:
                        System.out.println();
                        Colour.ChangeColour("g");
                        System.out.println(" Your current playlist :");
                        Colour.ChangeColour("cr");
                        main1.FillUserPlaylistSong();
                        System.out.println(" " + Main1.fetchUserSong);
                        Colour.ChangeColour("g");
                        System.out.print(" Enter the song you want to delete : ");
                        Colour.ChangeColour("cr");
                        String SongName = sc.nextLine().toUpperCase();
                        deleteSong(SongName);
                        break;
    
                    case 3:
                        System.out.println();
                        Colour.ChangeColour("y");
                        System.out.println("\n Deleting the current playlist...");
                        Thread.currentThread().sleep(2000);
                        Colour.ChangeColour("cr");
                        deleteFullPlaylist();
                        MakeYourOwnPlaylist();
                        break;
    
                    case 4:
                    System.out.println();
                        Colour.ChangeColour("y");
                        System.out.println("\n Exiting after updation of playlist... ");
                        Colour.ChangeColour("cr");
                        System.out.println();
                        break;
    
                    default:
                        System.out.println();
                        Colour.ChangeColour("r");
                        System.out.println(" Enter your choice between 1 to 3");
                        Colour.ChangeColour("cr");
                        break;
                }
            } catch (Exception e) {
                Colour.ChangeColour("r");
                System.out.println("\n Invalid Input Entered");
                Colour.ChangeColour("r");
                sc.nextLine();
            }
        } while (choice != 4);
    }
    

    void PlayYourOwnPlaylist() throws SQLException {
        playUserPlaylist.clear();
        main1.FillUserPlaylistSong();
        addUserPlaylistSong();
        int choice = 0;
        do {
            try {
                System.out.println("--------------------------------------------------------------");
                System.out.print("\n ");
                Colour.ChangeColour("bk,gb");
                System.out.println("Playing user's playlist song : ");
                Colour.ChangeColour("cr");
                Colour.ChangeColour("g");
                System.out.print("\n 1.Play next song \n 2.Play previous song \n 3.Shuffle the song \n 4.Display user playlist \n 5.Exit \n Enter your choice: ");
                Colour.ChangeColour("cr");
                choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        System.out.println();
                        PlayNextSongInPlaylist();
                        break;
    
                    case 2:
                        System.out.println();
                        PlayPreviousSongInPlaylist();
                        break;
    
                    case 3:
                        System.out.println();
                        ShuffleUserPlaylist();
                        break;
    
                    case 4:
                        System.out.println();
                        main1.FillUserPlaylistSong();
                        DisplayUserPlaylist();
                        System.out.println();
                        break;
    
                    case 5:
                        Colour.ChangeColour("y");
                        System.out.println("\n Exiting the user's playlist...");
                        Colour.ChangeColour("cr");
                        System.out.println();
                        break;
    
                    default:
                        System.out.println();
                        Colour.ChangeColour("r");
                        System.out.print("\n Enter choice between 1 to 4 ");
                        Colour.ChangeColour("cr");
                        break;
                }
            } catch (Exception e) {
                Colour.ChangeColour("r");
                System.out.println("\n Invalid Input Entered");
                Colour.ChangeColour("cr");
                sc.nextLine();
            }
        } while (choice != 5);
    }
    

    ArrayDeque <String> playUserPlaylist=new ArrayDeque<>();

    void addUserPlaylistSong()
    {
        playUserPlaylist.addAll(Main1.fetchUserSongPath);
    }

    void PlayNextSongInPlaylist() {
        if (!playUserPlaylist.isEmpty()) {
            String nextSong = playUserPlaylist.removeFirst();
            PlaySong(nextSong);
            playUserPlaylist.addLast(nextSong);
        } else {
            Colour.ChangeColour("r");
            System.out.println("\n Playlist is empty.");
            Colour.ChangeColour("cr");
        }
    }

    void PlayPreviousSongInPlaylist() {
        if (!playUserPlaylist.isEmpty()) {
            String previousSong = playUserPlaylist.removeLast();
            PlaySong(previousSong);
            playUserPlaylist.addFirst(previousSong);
        } else {
            Colour.ChangeColour("r");
            System.out.println("\n Playlist is empty.");
            Colour.ChangeColour("cr");
        }
    }

    void ShuffleUserPlaylist() {
        if (!playUserPlaylist.isEmpty()) {
            ArrayList<String> al = new ArrayList<>(playUserPlaylist);
            playUserPlaylist.clear();
            Collections.shuffle(al);
            playUserPlaylist = new ArrayDeque<>(al);
            System.out.println("\n Playlist shuffled.");
        } else {
            Colour.ChangeColour("r");
            System.out.println("\n Playlist is empty.");
            Colour.ChangeColour("cr");
        }
    }

    void DisplayUserPlaylist() throws SQLException {

        try{
            String sql="{call DisplayUserPlaylistSong(?)}";
            CallableStatement cst=con.prepareCall(sql);
            cst.setString(1, User1.userId);
            ResultSet rs=cst.executeQuery();
            Colour.ChangeColour("g");
            System.out.println("\n Displaying user's playlist song : ");
            Colour.ChangeColour("cr");
            while(rs.next())
                {
                    System.out.print("\n "+rs.getString("SongName"));
                }
            System.out.println();
            }
            catch(Exception e)
            {
                Colour.ChangeColour("r");
                System.out.println(e);
                Colour.ChangeColour("cr");
                sc.nextLine();
            }
    }

    void PlaySong(String nextSong) {
        try {
            File audioFile = new File(nextSong);
            if (!audioFile.exists() || !audioFile.isFile()) {
                System.out.println("File not found: " + nextSong);
                return;
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            for(String SongID: Main1.sidlist){
                try{
                    String sql="{call DisplaySongDetails(?,?)}";
                    CallableStatement cst=con.prepareCall(sql);
                    cst.setString(1, SongID);
                    cst.setString(2, nextSong);
                    ResultSet rs=cst.executeQuery();
                    while(rs.next())
                        {
                            System.out.println("\n --------------------------------------------------------------");
                            System.out.print("\n Playing Song : "+rs.getString("SongName"));
                            System.out.print("\n Singer Name  : "+rs.getString("SingerName"));
                            System.out.print("\n Duration     : "+rs.getString("Duration")+"\n ");
                            System.out.print("\n --------------------------------------------------------------");
                        }
                    }
                    catch(Exception e)
                    {
                        Colour.ChangeColour("r");
                        System.out.println(e);
                        Colour.ChangeColour("cr");
                        sc.nextLine();
                    }
            }
            System.out.println();
            clip.open(audioStream);
            clip.start();
            System.out.print(" Press Enter to stop the song...");
            sc.nextLine();
            clip.stop();
            clip.close();
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Unsupported audio file format: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error while playing the file: " + e.getMessage());
        } catch (LineUnavailableException e) {
            System.out.println("Audio line unavailable: " + e.getMessage());
        }
    }
}
