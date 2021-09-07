import React from 'react'
import styled from 'styled-components'
import LoginForm from '../../molecule/LoginForm';

const BoxContainer = styled.div`
  width: 280px;
  min-height: 550px;
  display: flex;
  flex-direction: column;
  border-radius: 19px;
  background-color: #fff;
  box-shadow: 0 0 2px rgba(15, 15, 15, 0.28);
  position: relative;
  overflow: hidden;
`;

const TopContainer = styled.div`
  width: 100%;
  height: 250px;
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  padding: 0 1.8em;
  padding-bottom: 5em;
`;

const BackDrop = styled.div`
  width: 160%;
  height: 550px;
  position: absolute;
  display: flex;
  flex-direction: column;
  border-radius: 50%;
  transform: rotate(60deg);
  top: -290px;
  left: -70px;
  background: rgb(241, 196, 15);
  background: linear-gradient(
    58deg,
    rgba(241, 196, 15, 1),
    rgba(243, 172, 18, 1)
  );
`;

const HeaderContainer = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
`;

const HeaderText = styled.h2`
  font-size: 1.875rem;
  font-weight: 600;
  line-height: 1.24;
  z-index: 2;
  margin: 0;
  color: #fff;
`;

const SmallText = styled.h5`
  color: #fff;
  font-weight: 500;
  font-weight: .6875rem;
  z-index: 2;
  margin: 0;
  margin-top: .375rem;
`;

const InnerContainer = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column; 
  padding: 0px .8em;
`;

function AccountBox(props) {
  return (
    <BoxContainer>
      <TopContainer>
        <BackDrop />
        <HeaderContainer>
          <HeaderText>Welcome</HeaderText>
          <HeaderText>Back</HeaderText>
          <SmallText>Please Sign-in to continue</SmallText>
        </HeaderContainer>
      </TopContainer>
      <InnerContainer>
        <LoginForm />
      </InnerContainer>
    </BoxContainer>
  )
}

export default AccountBox
