package com.group.commitapp.user.domain;

import com.group.commitapp.group.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id = null;

  private String name;

  private String providerId;

  @Enumerated(value = EnumType.STRING)
  private Role role;

  private User(String name, String providerId) {
    this.name = name;
    this.providerId = providerId;
    this.role = Role.ROLE_USER;
  }

  public static User of(String name, String providerId) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다", name));
    }
    return new User(name, providerId);
  }

  //그룹
//  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY) //연관 관계의 주인이 아님을 명시
//  private List<Group> groups = new ArrayList<>();


//  Group group = new Group();

  //멤버
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY) //연관 관계의 주인이 아님을 명시
  private List<Member> members= new ArrayList<>();

//  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//  private List<UserLoanHistory> userLoanHistories = new ArrayList<>();

    public void updateName(String name) {
    this.name = name;
  }

//  public void loanBook(String bookName) {
//    this.userLoanHistories.add(new UserLoanHistory(this, bookName));
//  }
//
//  public void returnBook(String bookName) {
//    UserLoanHistory targetHistory = this.userLoanHistories.stream()
//        .filter(history -> history.getBookName().equals(bookName))
//        .findFirst()
//        .orElseThrow(IllegalArgumentException::new);
//    targetHistory.doReturn();
//  }

}
