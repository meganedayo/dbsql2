package oit.is.kadai5.dbsql2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Sample3AuthConfiguration {
  /**
   * 認証処理に関する設定（誰がどのようなロールでログインできるか）
   *
   * @return
   */
  @Bean
  public InMemoryUserDetailsManager userDetailsService() {

    // ユーザ名，パスワード，ロールを指定してbuildする
    // このときパスワードはBCryptでハッシュ化されているため，{bcrypt}とつける
    // ハッシュ化せずに平文でパスワードを指定する場合は{noop}をつける
    // user1/p@ss,user2/p@ss,admin/p@ss

    UserDetails tanuki = User.withUsername("tanuki")
        .password("{bcrypt}$2y$05$Q//zM7bB5OF7y3mCkoccx.FlLT5Hq6Y2szs/wHLEp/yQugrEHY8a2").roles("USER").build();
    UserDetails kitune = User.withUsername("kitune")
        .password("{bcrypt}$2y$05$mpPEva8HoaceYP1LKkV72.lmaxrG6Zux1LjWao8hhX17sjw7RiNuO").roles("USER").build();
        UserDetails araiguma = User.withUsername("araiguma")
        .password("{bcrypt}$2y$05$GtxoRH1hQwQmgTgtFCxEwONOc5lasqd9reZa.hH1Qz0XdVkrkfLri").roles("USER").build();
    UserDetails admin = User.withUsername("admin")
        .password("{bcrypt}$2y$10$ngxCDmuVK1TaGchiYQfJ1OAKkd64IH6skGsNw1sLabrTICOHPxC0e").roles("ADMIN").build();
    // customer1 p@ss
    UserDetails customer1 = User.withUsername("customer1")
        .password("{bcrypt}$2y$10$ngxCDmuVK1TaGchiYQfJ1OAKkd64IH6skGsNw1sLabrTICOHPxC0e")
        .roles("CUSTOMER")
        .build();
    // customer2 p@ss
    UserDetails customer2 = User.withUsername("customer2")
        .password("{bcrypt}$2y$10$ngxCDmuVK1TaGchiYQfJ1OAKkd64IH6skGsNw1sLabrTICOHPxC0e")
        .roles("CUSTOMER")
        .build();
    // seller p@ss
    UserDetails seller = User.withUsername("seller")
        .password("{bcrypt}$2y$10$ngxCDmuVK1TaGchiYQfJ1OAKkd64IH6skGsNw1sLabrTICOHPxC0e")
        .roles("SELLER")
        .build();

    // 生成したユーザをImMemoryUserDetailsManagerに渡す（いくつでも良い）
    return new InMemoryUserDetailsManager(tanuki, kitune, araiguma, admin, customer1, customer2, seller);
  }
}
