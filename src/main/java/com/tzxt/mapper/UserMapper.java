
package com.tzxt.mapper;

import com.tzxt.util.MyMapper;
import com.tzxt.model.User;

/**
 * @author liuzh_3nofxnp
 * @since 2016-01-22 22:17
 */
public interface UserMapper extends MyMapper<User> {
    User selectByName(String userName);
}
