## 🐋 백경이

<div align="center">
<img src="https://github.com/tgyuuAn/Baekyoung-i/assets/116813010/02f38685-292b-4f15-935b-7c4d003190e8" width = 500>
</div>

<br><br><br>

## 🌟 주요 기능

<br><br><br>
## 🧑‍🤝‍🧑 팀원

| [안태규](https://github.com/tgyuuAn) | 박준형 | 신상후 | 김종현 |
| :---: | :---: | :---: | :---: |
|안드로이드| AI | AI | 디자인 |

<br><br><br>
## 🎨 UI/UX

<a href="https://www.figma.com/file/z7dilPOmXYdRUc8KhICWnw/%EC%B1%97%EB%B4%87%EC%96%B4%ED%94%8C-%EB%B0%B1%EA%B2%BD%EC%9D%B4?type=design&node-id=0%3A1&mode=design&t=QXZRcBkarEtVyH9d-1">Figma Link</a>

<br><br><br>
## 🏗️ Android 앱 아키텍처

- **MVI**
  
- **AAC**
  
- **App + Presentation + domain + data 모듈화**
  
<br><br><br>

## 📊 모듈 의존성 그래프
![백경이 모듈 의존성 그래프 drawio](https://github.com/tgyuuAn/Baekyoung-i/assets/116813010/0f04c57d-8c48-4a33-a798-64969f3ecf35)

<br><br><br>

## 🔧 Android 사용할 기술

- **UI** : Compose + navigation + ViewModel + Glide + Lottie + Material

- **DI** : hilt

- **Local** : Room + Prefrences dataStore
  
- **Remote** : Retrofit2 + OkHttp3 + Kotlinx Serialization

- **Test** : Junit + mockk

- **Firebase** : Analytics + Crashlytics + Authentication + Realtime DataBase
 
<br><br><br>

 ## 🤝 Git Convention
 
 ### 📤Commit Convention
 
- ```chore``` : 동작에 영향 없는 코드 or 변경 없는 변경사항(주석 추가 등)
- ```feat``` : 새로운 기능 구현
- ```add``` : Feat 이외의 부수적인 코드 추가, 라이브러리 추가, 새로운 파일 생성
- ```fix``` : 버그, 오류 해결
- ```del``` : 쓸모없는 코드 삭제
- ```docs``` : README나 WIKI 등의 문서 수정
- ```correct``` : 주로 문법의 오류나 타입의 변경, 이름 변경시
- ```rename``` : 파일 이름 변경시
- ```refactor``` : 전면 수정
- ```merge```: 다른 브랜치와 병합
  
ex ) ```git commit -m "feat/#1 : 회원가입 기능 완료"```

<br>

### 🌲 Branch Convention

- ```[develop]``` : 최종 배포
- ```[feature]``` : 기능 추가
- ```[fix]``` : 에러 수정, 버그 수정
- ```[docs]``` : README, 문서
- ```[refactor]``` : 코드 리펙토링 (기능 변경 없이 코드만 수정할 때)
- ```[modify]``` : 코드 수정 (기능의 변화가 있을 때)
- ```[chore]``` : gradle 세팅, 위의 것 이외에 거의 모든 것

ex) ```feature/#issue-user-api```

<br>

## 🌊 Git Flow

기본적으로 Git Flow 전략을 이용한다. 작업 시작 시 선행되어야 할 작업은 다음과 같다.

``` kotlin
1. Issue를 생성한다.
2. feature Branch를 생성한다.
3. Add - Commit - Push - Pull Request 의 과정을 거친다.
4. merge된 작업이 있을 경우, 다른 브랜치에서 작업을 진행 중이던 개발자는 본인의 브랜치로 merge된 작업을 Pull 받아온다.
5. 종료된 Issue와 Pull Request의 Label과 Project를 관리한다.
```
