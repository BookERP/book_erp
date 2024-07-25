CREATE TABLE book (
                      book_id NUMBER PRIMARY KEY,
                      isbn VARCHAR2(13) NOT NULL,
                      title VARCHAR2(100) NOT NULL,
                      author VARCHAR2(50) NOT NULL,
                      publisher VARCHAR2(50) NOT NULL,
                      publish_date DATE NOT NULL,
                      price NUMBER(10,2) NOT NULL,
                      category VARCHAR2(50) NOT NULL,
                      stock NUMBER NOT NULL
);
