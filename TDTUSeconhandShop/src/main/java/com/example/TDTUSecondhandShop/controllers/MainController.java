package com.example.TDTUSecondhandShop.controllers;
import com.example.TDTUSecondhandShop.untils.generateID;
import com.example.TDTUSecondhandShop.DAO.*;
import com.example.TDTUSecondhandShop.models.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController

@RequestMapping("/api")
public class MainController {
//    @CrossOrigin(origins = "http://localhost:3000")
//    @CrossOrigin

    //LOGIN
    // POST [/check-login] => check tài khoản mới // role // đã check api
    @RequestMapping(value="/check-login", method = {RequestMethod.GET, RequestMethod.POST})
    public String checkFirstLogin(ModelMap modelMap,
                                @RequestParam(value="email") String email,
                                @RequestParam(value="name") String name)
            throws SQLException, ClassNotFoundException
    {
        AccountDAO ac = new AccountDAO();
        UserDAO us = new UserDAO();
        if(ac.checkEmail(email)){
            ac.addAccount(email);
            us.addUser(email,name);
        }
        Account account = ac.getAccount(email);
        if (account.getStatus().equals("INACTIVE")){
            return "error";
        }
        if(account.getRole().equals("ADMIN")){
            return "ADMIN";
        } else if (account.getRole().equals("MANAGER")) {
            return "MANAGER";
        } else if (account.getRole().equals("USER")) {
            return "USER";
        } else {
            return "error";
        }
    }



    //ADMIN
    //ACCOUNT
    // GET [/admin/home] => Hiển thị trang chủ admin
    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public List<Account> showAdminHomePage(ModelMap modelMap) throws SQLException, ClassNotFoundException
    {
        AccountDAO list = new AccountDAO();
        List<Account> account = list.getAllAccount();
        return account;
    }
    // POST [/admin/account/edit/?email] => Sửa account
    @RequestMapping(value="/admin/home/edit", method = RequestMethod.POST)
    public List<Account> editAccountAdmin(ModelMap modelMap,
                               @RequestParam(value="email") String email,
                               @RequestParam(value="status") String status,
                                    @RequestParam(value="role") String role)
            throws SQLException, ClassNotFoundException
    {
        AccountDAO list = new AccountDAO();
        list.updateAccount(email, role, status);
        List<Account> account = list.getAllAccount();
        return account;
    }
    //search
    @RequestMapping(value="/admin/account/search", method = RequestMethod.POST)
    public List<Account> searcAccount(ModelMap modelMap,
                                      @RequestParam(value="string") String string) throws SQLException, ClassNotFoundException
    {
        AccountDAO list = new AccountDAO();
        List<Account> account = list.searchAccount(string);
        return account;
    }

    //USER
    // GET [/admin/user] => Hiển thị trang danh sách user của admin
    @RequestMapping(value="/admin/user", method = RequestMethod.GET)
    public List<User> showAdminUserPage(ModelMap modelMap) throws SQLException, ClassNotFoundException
    {
        UserDAO listUser = new UserDAO();
        List<User> user = listUser.getAllActiveUser();
        return user;
    }
    // GET [/admin/user/profile/?email] => Hiển thị trang user của admin
    @RequestMapping(value="/admin/user/detail", method = RequestMethod.GET)
    public User showUserDetailAdmin(ModelMap modelMap,
                                    @RequestParam(value="email") String email
    )
            throws SQLException, ClassNotFoundException
    {
        UserDAO listUser = new UserDAO();
        User user = listUser.getUser(email);
        return user;
    }


    // POST
    //GET [/admin/post/?postid=] => Hiển thị chi tiết post của admin
    @RequestMapping(value="/admin/post/detail", method = RequestMethod.GET)
    public Post showPostDetailAdmin(ModelMap modelMap,
                                          @RequestParam(value = "postid") String postID)
            throws SQLException, ClassNotFoundException
    {
        PostDAO list = new PostDAO();
        Post post = list.getPost(postID);
        return post;
    }
    // GET [/admin/post/delete/?id=id] => Xóa post admin
    @RequestMapping(value="/admin/post/delete", method = RequestMethod.GET)
    public List<PostReport> deletePostAdmin(ModelMap modelMap,
                             @RequestParam(value = "postid") String postID)
            throws SQLException, ClassNotFoundException
    {
        PostDAO list = new PostDAO();
        list.deletePost(postID);
        PostReportDAO listPostReport = new PostReportDAO();
        List<PostReport> postreport = listPostReport.getAllPostReportUnread();
        return postreport;
    }
    //POST REPORT
    // GET [/admin/postreport] => Hiển thị trang danh sách report bài viết của admin
    @RequestMapping(value="/admin/postreport", method = RequestMethod.GET)
    public List<PostReport> showAdminPostReportPage(ModelMap modelMap) throws SQLException, ClassNotFoundException
    {
        PostReportDAO listPostReport = new PostReportDAO();
        List<PostReport> postreport = listPostReport.getAllPostReportUnread();
        return postreport;
    }
    // GET [/admin/postreport/detail/?reportid] => Hiển thị report bài viết chi tiết của admin
    @RequestMapping(value="/admin/postreport/detail", method = RequestMethod.GET)
    public PostReport showAdminPostReportDetail(ModelMap modelMap,
                                                @RequestParam(value="reportid") int reportID)
            throws SQLException, ClassNotFoundException
    {
        PostReportDAO listPostReport = new PostReportDAO();
        PostReport postreport = listPostReport.getPostReport(reportID);
        return postreport;
    }
    // POST [/admin/postreport/edit/?reportid] => Sửa trạng thái UNREAD sang DONE post report
    @RequestMapping(value="/admin/postreport/edit", method = RequestMethod.POST)
    public List<PostReport> editPostReportAdmin(ModelMap modelMap,
                            @RequestParam(value="reportid") int reportID,
                            @RequestParam(value="status") String status)
            throws SQLException, ClassNotFoundException
    {
        PostReportDAO listPostReport = new PostReportDAO();
        listPostReport.updatePostReport(reportID, status);
        List<PostReport> postreport = listPostReport.getAllPostReportUnread();
        return postreport;
    }

    //SHOP REPORT
    // GET [/admin/shopreport] => Hiển thị trang danh sách report shop của admin
    @RequestMapping(value="/admin/shopreport", method = RequestMethod.GET)
    public List<ShopReport> showAdminShopReportPage(ModelMap modelMap) throws SQLException, ClassNotFoundException
    {
        ShopReportDAO listShopReport = new ShopReportDAO();
        List<ShopReport> shopReport = listShopReport.getAllShopReportUnread();
        return shopReport;
    }
    // GET [/admin/shopreport/detail/?reportid] => Hiển thị report bài viết chi tiết của admin
    @RequestMapping(value="/admin/shopreport/detail", method = RequestMethod.GET)
    public ShopReport showAdminShopReportDetail(ModelMap modelMap,
                                                @RequestParam(value="reportid") int reportID)
            throws SQLException, ClassNotFoundException
    {
        ShopReportDAO listShopReport = new ShopReportDAO();
        ShopReport shopreport = listShopReport.getShopReport(reportID);
        return shopreport;
    }
    //  POST [/admin/shopreport/edit/?reportid] => Sửa trạng thái UNREAD sang DONE shop report
    @RequestMapping(value="/admin/shopreport/edit", method = RequestMethod.POST)
    public List<ShopReport> editShopReportAdmin(ModelMap modelMap,
                               @RequestParam(value="reportid") int reportID,
                               @RequestParam(value="status") String status) throws SQLException, ClassNotFoundException
    {
        ShopReportDAO listShopReport = new ShopReportDAO();
        listShopReport.updateShopReport(reportID, status);
        List<ShopReport> shopreport = listShopReport.getAllShopReportUnread();
        return shopreport;
    }

    //TYPE
    // GET [/admin/type] => Hiển thị trang danh sách type của admin
    @RequestMapping(value="/admin/type", method = RequestMethod.GET)
    public List<Type> showAdminTypePage(ModelMap modelMap) throws SQLException, ClassNotFoundException
    {
        TypeDAO list = new TypeDAO();
        List<Type> type = list.getAllType();
        return type;
    }
    //  POST [/admin/type/add] => Tạo type
    @RequestMapping(value="/admin/type/add", method = RequestMethod.POST)
    public List<Type> addTypeAdmin(ModelMap modelMap,
                              @RequestParam(value="typeid") String typeID,
                              @RequestParam(value="name") String name) throws SQLException, ClassNotFoundException
    {
        TypeDAO list = new TypeDAO();
        list.addType(typeID, name);
        List<Type> type = list.getAllType();
        return type;
    }
    //  POST [/admin/type/edit/?typeid] => Sửa tên type
    @RequestMapping(value="/admin/type/edit", method = RequestMethod.POST)
    public List<Type> editTypeAdmin(ModelMap modelMap,
                                    @RequestParam(value="typeid") String typeID,
                                    @RequestParam(value="name") String name) throws SQLException, ClassNotFoundException
    {
        TypeDAO list = new TypeDAO();
        list.updateType(typeID, name);
        List<Type> type = list.getAllType();
        return type;
    }
    //  POST [/admin/type/delete/?typeid] => Xóa type
    @RequestMapping(value="/admin/type/delete", method = RequestMethod.POST)
    public List<Type> deleteTypeAdmin(ModelMap modelMap,
                              @RequestParam(value="typeid") String typeID) throws SQLException, ClassNotFoundException
    {
        TypeDAO list = new TypeDAO();
        list.deleteType(typeID);
        List<Type> type = list.getAllType();
        return type;
    }










    //MANAGER
    //POST
    //GET [/manager/home] => Hiển thị danh sách post của manager
    @RequestMapping(value="/manager/home", method = RequestMethod.GET)
    public List<Post> showManagerHomePage(ModelMap modelMap,
                                          @RequestParam(value = "email") String email)
            throws SQLException, ClassNotFoundException
    {
        PostDAO list = new PostDAO();
        List<Post> post = list.getAllPostSelling(email);
        return post;
    }
    //GET [/manager/home/filter/?typeID] => Hiển thị danh sách post của manager
    @RequestMapping(value="/manager/filter", method = RequestMethod.GET)
    public List<Post> showManagerHomePageType(ModelMap modelMap,
                                          @RequestParam(value = "email") String email,
                                          @RequestParam(value = "typeid") String typeid)
            throws SQLException, ClassNotFoundException
    {
        PostDAO list = new PostDAO();
        List<Post> post = list.getAllPostSellingType(email,typeid);
        return post;
    }
    //GET [/manager/post/detail/?postid] => Hiển thị chi tiết post của manager
    @RequestMapping(value="/manager/post/detail", method = RequestMethod.GET)
    public Post showPostDetailManager(ModelMap modelMap,
                               @RequestParam(value = "postid") String postID)
            throws SQLException, ClassNotFoundException
    {
        PostDAO list = new PostDAO();
        Post post = list.getPost(postID);
        return post;
    }
    // POST [/manager/post/delete/?id=id] => Xóa post user
    @RequestMapping(value="/manager/home/post/delete", method = RequestMethod.POST)
    public List<Post>  deletePostOfUserManager(ModelMap modelMap,
                                             @RequestParam(value = "postid") String postID,
                                             @RequestParam(value="email") String email)
            throws SQLException, ClassNotFoundException
    {
        PostDAO list = new PostDAO();
        list.deletePost(postID);
        List<Post> post = list.getAllPostSelling(email);
        return post;
    }
    //POST
    //GET [/manager/bought/?email] => Hiển thị danh sách sản phẩm đã mua
    @RequestMapping(value="/manager/bought", method = RequestMethod.GET)
    public List<Post> showUserBoughtPageManager(ModelMap modelMap,
                                         @RequestParam(value = "email") String email)
            throws SQLException, ClassNotFoundException
    {
        PostDAO list = new PostDAO();
        List<Post> post = list.getAllPostBuy(email);
        return post;
    }
    //GET [/manager/sell/?email] => Hiển thị danh sách sản phẩm đang và đã bán
    @RequestMapping(value="/manager/sell", method = RequestMethod.GET)
    public List<Post> showUserSellManager(ModelMap modelMap,
                                   @RequestParam(value = "email") String email)
            throws SQLException, ClassNotFoundException
    {
        PostDAO list = new PostDAO();
        List<Post> post = list.getAllPostSell(email);
        return post;
    }
    // POST [/manager/post/create] => Tạo post user
    @RequestMapping(value="/manager/post/create", method = RequestMethod.POST)
    public void createPostUserManager(ModelMap modelMap,
                               @RequestParam(value = "email-shop") String emailShop,
                               @RequestParam(value = "type") String type,
                               @RequestParam(value = "title") String title,
                               @RequestParam(value = "price") String price,
                               @RequestParam(value = "description") String description,
                                      @RequestParam("image1") String image1,
                                      @RequestParam("image2") String image2,
                                      @RequestParam("image3") String image3,
                                      @RequestParam("video") String video)
            throws SQLException, ClassNotFoundException, IOException, NoSuchAlgorithmException
    {
        PostDAO list = new PostDAO();
        String postID = generateID.getRandomString(10);

        list.addPost(postID,emailShop,type,title,price,description);

        if(!image1.equals("")) {
            ImageDAO img1 = new ImageDAO();
            img1.addImage(postID, image1);
        }
        if(!image2.equals("")) {
            ImageDAO img1 = new ImageDAO();
            img1.addImage(postID, image2);
        }
        if(!image3.equals("")) {
            ImageDAO img1 = new ImageDAO();
            img1.addImage(postID, image3);
        }

        if(!video.equals("")) {
            VideoDAO vid = new VideoDAO();
            vid.addVideo(postID, video);
        }

    }

    // POST [/manager/post/edit/?id=id] => Sửa post user
    @RequestMapping(value="/manager/post/edit", method = RequestMethod.POST)
    public List<Post> updatePostUserManager(ModelMap modelMap,
                               @RequestParam(value = "postid") String postID,
                               @RequestParam(value = "type") String type,
                               @RequestParam(value = "title") String title,
                               @RequestParam(value = "price") String price,
                               @RequestParam(value = "description") String description,
                                            @RequestParam(value="email") String email)
            throws SQLException, ClassNotFoundException
    {
        PostDAO list = new PostDAO();
        list.updatePost(type,title,price,description,postID);
        List<Post> post = list.getAllPostSell(email);
        return post;
    }
    // POST [/manager/post/edit/?id=id] => Sửa status post user
    @RequestMapping(value="/manager/post/edit/status", method = RequestMethod.POST)
    public List<Post> updateStatusPostUserManager(ModelMap modelMap,
                                     @RequestParam(value = "postid") String postID,
                                     @RequestParam(value = "email-buy") String emailBuy,
                                               @RequestParam(value="email-shop") String emailShop)
            throws SQLException, ClassNotFoundException
    {
        PostDAO list = new PostDAO();
        list.updateStatusPost(postID,emailBuy);
        List<Post> post = list.getAllPostSell(emailShop);
        return post;
    }
    // POST [/manager/post/edit/?id=id] => Sửa rate post user (nằm bên trang bought)
    @RequestMapping(value="/manager/post/edit/rate", method = RequestMethod.POST)
    public  List<Post> updateRatePostUserManager(ModelMap modelMap,
                                   @RequestParam(value = "postid") String postID,
                                   @RequestParam(value = "rate") int rate,
                                             @RequestParam(value="email") String email)
            throws SQLException, ClassNotFoundException
    {
        PostDAO list = new PostDAO();
        list.updateRatingPost(postID,rate);
        List<Post> post = list.getAllPostBuy(email);
        return post;
    }
    // POST [/manager/post/delete/?id=id] => Xóa post user
    @RequestMapping(value="/manager/post/delete", method = RequestMethod.POST)
    public List<Post>  deletePostUserManager(ModelMap modelMap,
                               @RequestParam(value = "postid") String postID,
                                             @RequestParam(value="email") String email)
            throws SQLException, ClassNotFoundException
    {
        PostDAO list = new PostDAO();
        list.deletePost(postID);
        List<Post> post = list.getAllPostSell(email);
        return post;
    }
    //ACCOUNT
    // GET [/manager/account] => Hiển thị trang account của manager
    @RequestMapping(value="/manager/account", method = RequestMethod.GET)
    public List<Account> showManagerAccountPage(ModelMap modelMap) throws SQLException, ClassNotFoundException
    {
        AccountDAO list = new AccountDAO();
        List<Account> account = list.getAllAccount();
        return account;
    }
    // POST [/manager/account/edit-status/?email] => Sửa trạng thái account
    @RequestMapping(value="/manager/home/edit-status", method = RequestMethod.POST)
    public List<Account> editAccountManager(ModelMap modelMap,
                                 @RequestParam(value="email") String email,
                                 @RequestParam(value="status") String status) throws SQLException, ClassNotFoundException
    {
        AccountDAO list = new AccountDAO();
        list.updateStatusAccount(email, status);
        List<Account> account = list.getAllAccount();
        return account;
    }

    //USER
    // GET [/manager/user] => Hiển thị trang danh sách user của manager
    @RequestMapping(value="/manager/user", method = RequestMethod.GET)
    public List<User> showManagerUserPage(ModelMap modelMap) throws SQLException, ClassNotFoundException
    {
        UserDAO listUser = new UserDAO();
        List<User> user = listUser.getAllActiveUser();
        return user;
    }
    // GET [/manager/user/profile/?email] => Hiển thị trang user của manager
    @RequestMapping(value="/manager/user/detail", method = RequestMethod.GET)
    public User showUserDetailManager(ModelMap modelMap,
                                    @RequestParam(value="email") String email
    )
            throws SQLException, ClassNotFoundException
    {
        UserDAO listUser = new UserDAO();
        User user = listUser.getUser(email);
        return user;
    }
    //GET [/manager/user/search] => Search user
    @RequestMapping(value="/manager/user/search", method = RequestMethod.GET)
    public List<User> searchUserManager(ModelMap modelMap,
                                 @RequestParam(value = "string") String string)
            throws SQLException, ClassNotFoundException
    {
        UserDAO list = new UserDAO();
        List<User> user = list.searchUSer(string);
        return user;
    }
    //GET [/manager/profile] => Hiển thị thông tin của user
    @RequestMapping(value="/manager/profile", method = RequestMethod.GET)
    public User showUserProfileManager(ModelMap modelMap,
                                @RequestParam(value = "email") String email)
            throws SQLException, ClassNotFoundException
    {
        UserDAO list = new UserDAO();
        User user = list.getUser(email);
        return user;
    }
    //GET [/manager/profile/rate] => Hiển thị rate của user
    @RequestMapping(value="/manager/profile/rate", method = RequestMethod.GET)
    public double getRateManager(ModelMap modelMap,
                          @RequestParam(value = "email") String email)
            throws SQLException, ClassNotFoundException
    {
        PostDAO list = new PostDAO();
        double rate = list.getRate(email);
        return rate;
    }
    //POST [/manager/profile/edit/?email] => Sửa thông tin của user
    @RequestMapping(value="/manager/profile/edit", method = RequestMethod.POST)
    public User editUserProfileManager(ModelMap modelMap,
                                @RequestParam(value = "email") String email,
                                @RequestParam(value = "gender") String gender,
                                @RequestParam(value = "birthday") String birthday,
                                @RequestParam(value = "phone") String phone,
                                @RequestParam(value = "personal-email") String personalEmail,
                                @RequestParam(value = "gender-hidden") String genderHidden,
                                @RequestParam(value = "birthday-hidden") String birthdayHidden,
                                @RequestParam(value = "personal-email-hidden") String personalEmailHidden,
                                @RequestParam(value = "phone-hidden") String phoneHidden,
                                       @RequestParam("avatar") String avatar)
            throws SQLException, ClassNotFoundException, IOException, NoSuchAlgorithmException
    {
        UserDAO list = new UserDAO();
        list.updateUser(email,avatar,gender,birthday,phone,personalEmail,genderHidden,birthdayHidden,personalEmailHidden,phoneHidden);
        User user = list.getUser(email);
        return user;
    }
    //POST REPORT
    // GET [/manager/postreport] => Hiển thị trang danh sách report bài viết của manager
    @RequestMapping(value="/manager/postreport", method = RequestMethod.GET)
    public List<PostReport> showManagerPostReportPage(ModelMap modelMap) throws SQLException, ClassNotFoundException
    {
        PostReportDAO listPostReport = new PostReportDAO();
        List<PostReport> postreport = listPostReport.getAllPostReportUnread();
        return postreport;
    }
    // GET [/manager/postreport/detail/?reportid] => Hiển thị report bài viết chi tiết của manager
    @RequestMapping(value="/manager/postreport/detail", method = RequestMethod.GET)
    public PostReport showManagerPostReportDetail(ModelMap modelMap,
                                                @RequestParam(value="reportid") int reportID)
            throws SQLException, ClassNotFoundException
    {
        PostReportDAO listPostReport = new PostReportDAO();
        PostReport postreport = listPostReport.getPostReport(reportID);
        return postreport;
    }
    // POST [/manager/postreport/edit/?reportid] => Sửa trạng thái UNREAD sang DONE post report
    @RequestMapping(value="/manager/postreport/edit", method = RequestMethod.POST)
    public List<PostReport> editPostReportManager(ModelMap modelMap,
                                    @RequestParam(value="reportid") int reportID,
                                    @RequestParam(value="status") String status) throws SQLException, ClassNotFoundException
    {
        PostReportDAO listPostReport = new PostReportDAO();
        listPostReport.updatePostReport(reportID, status);
        List<PostReport> postreport = listPostReport.getAllPostReportUnread();
        return postreport;
    }

    // SHOP REPORT
    // GET [/manager/shopreport] => Hiển thị trang danh sách report shop của manager
    @RequestMapping(value="/manager/shopreport", method = RequestMethod.GET)
    public List<ShopReport> showManagerShopReportPage(ModelMap modelMap) throws SQLException, ClassNotFoundException
    {
        ShopReportDAO listShopReport = new ShopReportDAO();
        List<ShopReport> shopReport = listShopReport.getAllShopReportUnread();
        return shopReport;
    }
    // GET [/manager/shopreport/detail/?reportid] => Hiển thị report bài viết chi tiết của manager
    @RequestMapping(value="/manager/shopreport/detail", method = RequestMethod.GET)
    public ShopReport showManagerShopReportDetail(ModelMap modelMap,
                                                @RequestParam(value="reportid") int reportID)
            throws SQLException, ClassNotFoundException
    {
        ShopReportDAO listShopReport = new ShopReportDAO();
        ShopReport postreport = listShopReport.getShopReport(reportID);
        return postreport;
    }
    // POST [/manager/shopreport/edit/?reportid] => Sửa trạng thái UNREAD sang DONE shop report
    @RequestMapping(value="/manager/shopreport/edit", method = RequestMethod.POST)
    public List<ShopReport> editShopReportManager(ModelMap modelMap,
                                    @RequestParam(value="reportid") int reportID,
                                    @RequestParam(value="status") String status) throws SQLException, ClassNotFoundException
    {
        ShopReportDAO listShopReport = new ShopReportDAO();
        listShopReport.updateShopReport(reportID, status);
        List<ShopReport> shopReport = listShopReport.getAllShopReportUnread();
        return shopReport;
    }
    //IMAGE
    //GET [/manager/post/image] => Hiển thị hinh cua post
    @RequestMapping(value="/manager/post/image", method = RequestMethod.GET)
    public List<Image> showUserPostImageManager(ModelMap modelMap,
                                         @RequestParam(value = "postid") String postID)
            throws SQLException, ClassNotFoundException
    {
        ImageDAO list = new ImageDAO();
        List<Image> images = list.getAllImage(postID);
        return images;
    }
    //VIDEO
    //GET [/manager/post/video] => Hiển thị video cua post
    @RequestMapping(value="/manager/post/video", method = RequestMethod.GET)
    public List<Video> showUserPostVideoManager(ModelMap modelMap,
                                         @RequestParam(value = "postid") String postID)
            throws SQLException, ClassNotFoundException
    {
        VideoDAO list = new VideoDAO();
        List<Video> videos = list.getAllVideo(postID);
        return videos;
    }








































    //USER
    //GET [/user/home] => Hiển thị danh sách post của user
    @RequestMapping(value="/user/home", method = RequestMethod.GET)
    public List<Post> showUserHomePage(ModelMap modelMap,
                                       @RequestParam(value = "email") String email)
            throws SQLException, ClassNotFoundException
    {
        PostDAO list = new PostDAO();
        List<Post> post = list.getAllPostSelling(email);
        return post;
    }

    //USER
    //GET [/user/search] => Search user
    @RequestMapping(value="/user/search", method = RequestMethod.POST)
    public List<User> searchUser(ModelMap modelMap,
                                @RequestParam(value = "string") String string)
            throws SQLException, ClassNotFoundException
    {
        UserDAO list = new UserDAO();
        List<User> user = list.searchUSer(string);
        return user;
    }
    //GET [/user/profile] => Hiển thị thông tin của user
    @RequestMapping(value="/user/profile", method = RequestMethod.GET)
    public User showUserProfile(ModelMap modelMap,
                                    @RequestParam(value = "email") String email)
            throws SQLException, ClassNotFoundException
    {
       UserDAO list = new UserDAO();
       User user = list.getUser(email);
       return user;
    }
    // GET [/user/user/profile/?email] => Hiển thị trang user
    @RequestMapping(value="/user/user/detail", method = RequestMethod.GET)
    public User showUserDetailUser(ModelMap modelMap,
                                      @RequestParam(value="email") String email
    )
            throws SQLException, ClassNotFoundException
    {
        UserDAO listUser = new UserDAO();
        User user = listUser.getUser(email);
        return user;
    }
    //GET [/user/profile/rate] => Hiển thị rate của user
    @RequestMapping(value="/user/profile/rate", method = RequestMethod.GET)
    public double getRate(ModelMap modelMap,
                          @RequestParam(value = "email") String email)
            throws SQLException, ClassNotFoundException
    {
        PostDAO list = new PostDAO();
        double rate = list.getRate(email);
        return rate;
    }
    //POST [/user/profile/edit/?email] => Sửa thông tin của user
    @RequestMapping(value="/user/profile/edit", method = RequestMethod.POST)
    public User editUserProfile(ModelMap modelMap,
                                @RequestParam(value = "email") String email,
                                @RequestParam(value = "gender") String gender,
                                @RequestParam(value = "birthday") String birthday,
                                @RequestParam(value = "phone") String phone,
                                @RequestParam(value = "personal-email") String personalEmail,
                                @RequestParam(value = "gender-hidden") String genderHidden,
                                @RequestParam(value = "birthday-hidden") String birthdayHidden,
                                @RequestParam(value = "personal-email-hidden") String personalEmailHidden,
                                @RequestParam(value = "phone-hidden") String phoneHidden,
                                @RequestParam(value = "avatar") String avatar)
            throws SQLException, ClassNotFoundException, IOException, NoSuchAlgorithmException
    {
        UserDAO list = new UserDAO();
        list.updateUser(email,avatar,gender,birthday,phone,personalEmail,genderHidden,birthdayHidden,personalEmailHidden,phoneHidden);
        User user = list.getUser(email);
        return user;
    }


    //POST
    //GET [/user/home/filter/?typeID] => Hiển thị danh sách post của manager
    @RequestMapping(value="/user/filter", method = RequestMethod.POST)
    public List<Post> showUserHomePageType(ModelMap modelMap,
                                              @RequestParam(value = "email") String email,
                                              @RequestParam(value = "typeid") String typeid)
            throws SQLException, ClassNotFoundException
    {
        PostDAO list = new PostDAO();
        List<Post> post = list.getAllPostSellingType(email,typeid);
        return post;
    }
    //GET [/user/bought/?email] => Hiển thị danh sách sản phẩm đã mua // đã check api
    @RequestMapping(value="/user/bought", method = RequestMethod.GET)
    public List<Post> showUserBoughtPage(ModelMap modelMap,
                                         @RequestParam(value = "email") String email)
            throws SQLException, ClassNotFoundException
    {
        PostDAO list = new PostDAO();
        List<Post> post = list.getAllPostBuy(email);
        return post;
    }
    //GET [/user/sell/?email] => Hiển thị danh sách sản phẩm đang và đã bán của user X
    @RequestMapping(value="/user/sell", method = RequestMethod.GET)
    public List<Post> showUserSell(ModelMap modelMap,
                                         @RequestParam(value = "email") String email)
            throws SQLException, ClassNotFoundException
    {
        PostDAO list = new PostDAO();
        List<Post> post = list.getAllPostSell(email);
        return post;
    }
    //GET [/user/post/detail/?postid] => Hiển thị chi tiết post của user
    @RequestMapping(value="/user/post/detail", method = RequestMethod.GET)
    public Post showPostDetailUser(ModelMap modelMap,
                                      @RequestParam(value = "postid") String postID)
            throws SQLException, ClassNotFoundException
    {
        PostDAO list = new PostDAO();
        Post post = list.getPost(postID);
        return post;
    }
    // POST [/user/post/create] => Tạo post user
    @RequestMapping(value="/user/post/create", method = RequestMethod.POST)
    public boolean createPostUser(ModelMap modelMap,
                               @RequestParam(value = "email-shop") String emailShop,
                               @RequestParam(value = "type") String type,
                               @RequestParam(value = "title") String title,
                               @RequestParam(value = "price") String price,
                               @RequestParam(value = "description") String description,
                              @RequestParam(value = "image1") String image1,
                              @RequestParam(value = "image2") String image2,
                              @RequestParam(value = "image3") String image3,
                              @RequestParam(value = "video") String video)
            throws SQLException, ClassNotFoundException, IOException, NoSuchAlgorithmException
    {
        String postID = generateID.getRandomString(10);
        PostDAO list = new PostDAO();
        list.addPost(postID,emailShop,type,title,price,description);
        if(!image1.equals("")) {
            ImageDAO img1 = new ImageDAO();
            img1.addImage(postID, image1);
        }
        if(!image2.equals("")) {
            ImageDAO img1 = new ImageDAO();
            img1.addImage(postID, image2);
        }
        if(!image3.equals("")) {
            ImageDAO img1 = new ImageDAO();
            img1.addImage(postID, image3);
        }
        if(!video.equals("")) {
            VideoDAO vid = new VideoDAO();
            vid.addVideo(postID, video);
        }
        return true;
    }
    // POST [/user/post/edit/?id=id] => Sửa post user
    @RequestMapping(value="/user/post/edit", method = RequestMethod.POST)
    public List<Post> updatePostUser(ModelMap modelMap,
                               @RequestParam(value = "postid") String postID,
                               @RequestParam(value = "type") String type,
                               @RequestParam(value = "title") String title,
                               @RequestParam(value = "price") String price,
                               @RequestParam(value = "description") String description,
                                     @RequestParam(value = "email-shop") String emailShop)
            throws SQLException, ClassNotFoundException
    {
        PostDAO list = new PostDAO();
        list.updatePost(type,title,price,description,postID);
        List<Post> post = list.getAllPostSell(emailShop);
        return post;
    }
    // POST [/user/post/edit/?id=id] => Sửa status post user //đã check api
    @RequestMapping(value="/user/post/edit/status", method = RequestMethod.POST)
    public List<Post> updateStatusPostUser(ModelMap modelMap,
                               @RequestParam(value = "postid") String postID,
                               @RequestParam(value = "email-buy") String emailBuy,
                                        @RequestParam(value = "email-shop") String emailShop)
            throws SQLException, ClassNotFoundException
    {
        PostDAO list = new PostDAO();
        list.updateStatusPost(postID,emailBuy);
        List<Post> post = list.getAllPostSell(emailShop);
        return post;
    }
    // POST [/user/post/edit/?id=id] => Sửa rate post user (nằm bên trang bought) // đã check api
    @RequestMapping(value="/user/post/edit/rate", method = RequestMethod.POST)
    public List<Post> updateRatePostUser(ModelMap modelMap,
                                     @RequestParam(value = "postid") String postID,
                                     @RequestParam(value = "rate") int rate,
                                      @RequestParam(value = "email") String email)
            throws SQLException, ClassNotFoundException
    {
        PostDAO list = new PostDAO();
        list.updateRatingPost(postID,rate);
        List<Post> post = list.getAllPostBuy(email);
        return post;
    }
    // POST [/user/post/delete/?id=id] => Xóa post user
    @RequestMapping(value="/user/post/delete", method = RequestMethod.POST)
    public List<Post> deletePostUser(ModelMap modelMap,
                                  @RequestParam(value = "postid") String postID,
                                     @RequestParam(value = "email") String email)
            throws SQLException, ClassNotFoundException
    {
        PostDAO list = new PostDAO();
        list.deletePost(postID);
        List<Post> post = list.getAllPostSell(email);
        return post;
    }

    //POST REPORT
    //GET [/user/post-report/?email] => Hiển thị danh sách POST REPORT DA REPORT
    @RequestMapping(value="/user/post-report", method = RequestMethod.GET)
    public List<PostReport> showUserPostReportPage(ModelMap modelMap,
                                         @RequestParam(value = "email") String email)
            throws SQLException, ClassNotFoundException
    {
        PostReportDAO list = new PostReportDAO();
        List<PostReport> postReport = list.getAllPostReportUser(email);
        return postReport;
    }

    //POST [/user/post-report/] => REPORT POST
    @RequestMapping(value="/user/post-report/report", method = RequestMethod.POST)
    public boolean reportPostUser(ModelMap modelMap,
                                     @RequestParam(value = "postid") String postID,
                                     @RequestParam(value = "email") String email,
                                     @RequestParam(value = "description") String description)
            throws SQLException, ClassNotFoundException
    {
        PostReportDAO list = new PostReportDAO();
        list.addPostReport(postID,email,description);

        return true;
    }

    //SHOP REPORT
    //GET [/user/shop-report/?email] => Hiển thị danh sách shop REPORT DA REPORT
    @RequestMapping(value="/user/shop-report", method = RequestMethod.GET)
    public List<ShopReport> showUserShopReportPage(ModelMap modelMap,
                                                   @RequestParam(value = "email") String email)
            throws SQLException, ClassNotFoundException
    {
        ShopReportDAO list = new ShopReportDAO();
        List<ShopReport> shopReport = list.getAllShopReportUser(email);
        return shopReport;
    }

    //POST [/user/shop-report/] => report shop
    @RequestMapping(value="/user/shop-report/report", method = RequestMethod.POST)
    public boolean reportShopUser(ModelMap modelMap,
                               @RequestParam(value = "email-shop") String emailShop,
                               @RequestParam(value = "email-report") String emailReport,
                               @RequestParam(value = "description") String description)
            throws SQLException, ClassNotFoundException
    {
        ShopReportDAO list = new ShopReportDAO();
        list.addShopReport(emailShop,emailReport,description);

        return true;
    }


    //IMAGE
    //GET [/user/post/image] => Hiển thị hinh cua post
    @RequestMapping(value="/user/post/image", method = RequestMethod.GET)
    public List<Image> showUserPostImage(ModelMap modelMap,
                                                   @RequestParam(value = "postid") String postID)
            throws SQLException, ClassNotFoundException
    {
        ImageDAO list = new ImageDAO();
        List<Image> images = list.getAllImage(postID);
        return images;
    }
    //VIDEO
    //GET [/user/post/video] => Hiển thị video cua post
    @RequestMapping(value="/user/post/video", method = RequestMethod.GET)
    public List<Video> showUserPostVideo(ModelMap modelMap,
                                         @RequestParam(value = "postid") String postID)
            throws SQLException, ClassNotFoundException
    {
        VideoDAO list = new VideoDAO();
        List<Video> videos = list.getAllVideo(postID);
        return videos;
    }

}
