/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package content;

import java.io.File;
import java.util.Date;
import utils.AccessLevel;
import utils.User;

/**
 *
 * @author amclay2
 */
public class Post {
    private final User originalPoster;
    private final File picture;
    private final Date timestamp;
    private final AccessLevel minimumAccess;
    
    public Post(User user, File picture)
    {
        this.originalPoster = user;
        this.picture = picture;
        timestamp = new Date();
        minimumAccess = AccessLevel.NOOB;
    }
    
    public Post(User user, File picture, AccessLevel accessLevel)
    {
        this.originalPoster = user;
        this.picture = picture;
        this.minimumAccess = accessLevel;
        this.timestamp = new Date();
    }
    
    public User getPoster()
    {
        return originalPoster;
    }
    
    public File getPicture()
    {
        return picture;
    }
    
    public Date getPostTime()
    {
        return timestamp;
    }
    
    public boolean canAttend(User invitee)
    {
        return AccessLevel.hasAccess(invitee, minimumAccess);
    }
}
