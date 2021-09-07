import styled from 'styled-components'

export const BoxContainer = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: .625rem;
`;

export const FormContainer = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  box-shadow: 0px 0px 2.5px rgba(15, 15, 15, .19);
`;

export const MutedLink = styled.span`
  font-size: .75rem;
  color: rgba(200, 200, 200, .8);
  font-weight: 500;
  text-decoration: none;
`;

export const BoldLink = styled.a`
  font-size: .75rem;
  color: rgb(241, 196, 15);
  font-weight: 500;
  text-decoration: none;
`;

export const Input = styled.input`
  height: 2.625rem;
  width: 100%;
  outline: none;
  border: 1px solid rgba(200, 200, 200, 0.3);
  padding: 0px 10px;
  border-bottom: 1.4px solid transparent;
  transition: all 200ms ease-in-out;
  font-size: .75rem;

  &::placeholder {
    color: rgba(200, 200, 200, 1);
  }

  &::not(:last-of-type) {
    border-bottom: 1.5px solid rgba(200, 200, 200, 0.4);
  }

  &:focus {
    outline: none;
    border-bottom: 2px solid rgb(241, 196, 15);
  }
`;

export const SubmitButton = styled.button`
  width: 100%;
  padding: 11px 40%;
  color: #fff;
  font-size: 15px;
  font-weight: 600;
  border: none;
  border-radius: 100px;
  cursor: pointer;
  transition: all 240ms ease-in-out;
  background: rgb(241, 196, 15);
  background: linear-gradient(
    58deg,
    rgba(241, 196, 15, 1),
    rgba(243, 172, 18, 1)
  );

  &::hover {
    filter: brightness(1.03);
  }
`;