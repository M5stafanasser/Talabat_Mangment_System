package Talabat;

public abstract class User {
    private String userName ;
    private String passWord;
    private boolean loggedIn;


    public User(String userName, String passWord, boolean logedIn){
        setUserName(userName);
        setPassWord(passWord);
        setLoggedIn(logedIn);
    }

    public User(User user){
        this(new String(user.userName),
                new String(user.passWord),
                user.loggedIn);
    }

    public User(){
        this("","",false);
    }


    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }


    public void login(){
        this.loggedIn =true;
    }

    public void logout(){
        this.loggedIn =false;
    }

    @Override
    public boolean equals(Object obj) {
        User other = (User) obj;
        return this.userName.equals(other.getUserName());
    }
}
