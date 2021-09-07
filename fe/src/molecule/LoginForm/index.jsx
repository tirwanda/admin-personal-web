import React from 'react'
import { BoxContainer, FormContainer, Input, SubmitButton, MutedLink, BoldLink } from '../../atoms/Common'
import { Marginer } from '../../atoms/Marginer'

function LoginForm(props) {
  return (    
    <BoxContainer>
      <FormContainer>
        <Input placeholder="Email" type="email" />
        <Input placeholder="Password" type="password" />
      </FormContainer>
        <Marginer direction="vertical" margin={5} />
        <MutedLink href="#">Forget your password? <BoldLink href="#">Click Here</BoldLink></MutedLink>
        <Marginer direction="vertical" margin="1em" />
        <SubmitButton type="submit">SignIn</SubmitButton>
        <Marginer direction="vertical" margin="1em" />
        <MutedLink href="3">Don't have an account? <BoldLink href="#">SignUp</BoldLink></MutedLink>
    </BoxContainer>
  )
}

export default LoginForm
