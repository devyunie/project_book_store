# 온라인 서점 Project
## Project 설명
> 온라인 서점 Spring Boot 구현
- 스택
    - java 17
    - Spring Boot 3.3.5
    - Rest Api 이용

유저 입장에서 책을 구매하고, 리뷰와 평점을 작성하는 기능
## ERD 구성도
![DB ERD](https://github.com/user-attachments/assets/9a19d06a-2b72-4e65-b439-8fcf65ada805)
## book_store API 명세서
해당 API 명세서는  'book-store'의 REST를 명세하고 있습니다.
- Domain: http://localhost:4000
---
### Auth 모듈
book-store서비스의 인증 및 인가와 관련된 API모듈입니다.  
로그인, 회원가입 API가 포함되어있습니다.  
Auth 모듈은 인증 없이 요청할 수 있습니다.
- url: /users
---
 - 로그인  
설명 : 클라이언트는 사용자 아이디와 평문의 비밀번호를 입력하여 요청하고 아이디와 비밀번호가 일치한다면 인증에 사용될 token과 해당 token의 만료기간을 응답 데이터로 전달 받습니다 만약 아이디 혹은 비밀번호가 하나라고 틀리다면 로그인 정보 불일치에 해당하는 응답을 받게됩니다.  

- method : **POST**  
- end point : **/rogin**  

##### Request

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| userId | String | 사용자의 아이디 | O |
| password | String | 사용자의 비밀번호 | O |

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |
| accessToken | String | Bearer token 인증 방식에 사용될 JWT | O |
| expiration | Integer | JWT 만료 기간(초단위) | O |


---

 - 회원가입  
설명 : 클라이언트는 사용자 아이디와 평문의 비밀번호를 입력하여 요청하고 아이디와 비밀번호가 일치한다면 인증에 사용될 token과 해당 token의 만료기간을 응답 데이터로 전달 받습니다 만약 아이디 혹은 비밀번호가 하나라고 틀리다면 로그인 정보 불일치에 해당하는 응답을 받게됩니다.

- method : **POST**  
- end point : **/register**  

##### Request

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| userId | String | 사용자의 아이디 | O |
| password | String | 사용자의 비밀번호 | O |

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |
| accessToken | String | Bearer token 인증 방식에 사용될 JWT | O |
| expiration | Integer | JWT 만료 기간(초단위) | O |


---

### Books 모듈
Book 조회 추천 등의 API가 들어있습니다.  
해당 API는 인증 없이 사용이 가능합니다
- url: /books
---
#### 카테고리 조회    
설명 : 해당 API는 카테고리번호를 통해 조회를 하여 컬럼명을 통해 정렬이 가능합니다.

- method : **GET**  
- end point : **/?categoryNumber={categoryNumber}&?orderSet={컬럼}**  

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| bookList | `List<BookList>` | 북리스트 | O |

###### BookList
| name | type | description | required |
|---|:---:|:---:|:---:|
| bookNumber | Integer | 책번호 | O |
| bookName | String | 책이름 | O |
| author | String | 작가 | O |
| bookPrice | Integer | 책가격 | O |
| registrationDate | Date | 책 등록 날짜 | O |
| discountRate | Integer | 할인률 | O |
| categoryName | String | 카테고리 이름 | O |

---
#### 할인하는 책 조회    
설명 : 해당 API는 현재 할인중인 책을 조회하는 API 입니다

- method : **GET**  
- end point : **/discounts**

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| bookList | `List<BookList>` | 북리스트 | O |

###### BookList
| name | type | description | required |
|---|:---:|:---:|:---:|
| bookNumber | Integer | 책번호 | O |
| bookName | String | 책이름 | O |
| author | String | 작가 | O |
| bookPrice | Integer | 책가격 | O |
| registrationDate | Date | 책 등록 날짜 | O |
| discountRate | Integer | 할인률 | O |
| categoryName | String | 카테고리 이름 | O |

---

#### 베스트셀러 책 조회
설명 : 사용자들이 제일 많이 구매한 책을 추천하는 API입니다.

- method : **GET**  
- end point : **/recommend/best-seller**

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| bookList | `List<BookList>` | 북리스트 | O |

###### BookList
| name | type | description | required |
|---|:---:|:---:|:---:|
| bookNumber | Integer | 책번호 | O |
| bookName | String | 책이름 | O |
| author | String | 작가 | O |
| bookPrice | Integer | 책가격 | O |
| registrationDate | Date | 책 등록 날짜 | O |
| discountRate | Integer | 할인률 | O |
| categoryName | String | 카테고리 이름 | O |


---
#### 최근 등록 책 5개

설명 : 최근에 등록된 책 5개를 보여주는 API입니다.

- method : **GET**  
- end point : **/recommend/recently-books**

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| bookList | `List<BookList>` | 북리스트 | O |

###### BookList
| name | type | description | required |
|---|:---:|:---:|:---:|
| bookNumber | Integer | 책번호 | O |
| bookName | String | 책이름 | O |
| author | String | 작가 | O |
| bookPrice | Integer | 책가격 | O |
| registrationDate | Date | 책 등록 날짜 | O |
| discountRate | Integer | 할인률 | O |
| categoryName | String | 카테고리 이름 | O |

---

 #### 사용자 카테고리 책 추천 알고리즘
설명 : 사용자가 구매한 책들중 가장 많이 구매한 책의 카테고리를 찾아 해당 카테고리별 책을 추천해주는 서비스

- method : **GET**  
- end point : **/recommend/category-best-seller**

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| bookList | `List<BookList>` | 북리스트 | O |

###### BookList
| name | type | description | required |
|---|:---:|:---:|:---:|
| bookNumber | Integer | 책번호 | O |
| bookName | String | 책이름 | O |
| author | String | 작가 | O |
| bookPrice | Integer | 책가격 | O |
| registrationDate | Date | 책 등록 날짜 | O |
| discountRate | Integer | 할인률 | O |
| categoryName | String | 카테고리 이름 | O |

---

 #### 리뷰  
설명 : 사용자가 구매한 책들 중 리뷰를 작성하는 APi

- method : **POST**  
- end point : **/recommend/category-best-seller**

##### Request

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| rating | Double | 별점 | O |
| comment | String | 리뷰 내용 | O |

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 content type (application/json) | O |

###### Response Body

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |

