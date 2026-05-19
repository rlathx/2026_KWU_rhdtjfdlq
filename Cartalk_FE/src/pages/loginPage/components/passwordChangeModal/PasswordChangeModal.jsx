import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import axios from 'axios'
import InputField from '../../../../components/inputField/InputField'
import Button from '../../../../components/button/Button'
import StepIndicator from '../stepIndicator/StepIndicator'
import './PasswordChangeModal.css'

export default function PasswordChangeModal({ onClose }) {
  const navigate = useNavigate()
  const [step, setStep] = useState(1)

  const [email, setEmail] = useState('')
  const [code, setCode] = useState('')
  const [newPassword, setNewPassword] = useState('')
  const [confirmPassword, setConfirmPassword] = useState('')

  const [codeError, setCodeError] = useState(false)
  const [passwordError, setPasswordError] = useState(false)
  const [isLoading, setIsLoading] = useState(false)

  //이메일 인증코드 발송
  const handleSendEmail = async () => {
    if (isLoading) return //중복 클릭 방지
    setIsLoading(true)
    try {
      await axios.post('/api/auth/code', { email })
      alert('인증 코드가 발송되었습니다.')
      setStep(2)
      setCode('')
      setCodeError(false)
    } catch (error) {
      alert(error.response?.data?.message || '발송 실패')
    } finally {
      setIsLoading(false)
    }
  }

  //인증코드 확인
  const handleVerifyCode = async (targetCode) => {
    if (isLoading) return //중복 호출 방지
    setIsLoading(true)
    try {
      await axios.post('/api/auth/verify', { email, code: targetCode })
      setStep(3) //화면을 3단계로 전환
      setCodeError(false)
    } catch {
      setCodeError(true)
      setCode('') //틀리면 입력창 비우기
    } finally {
      setIsLoading(false)
    }
  }

  //onChange에서 6자리를 감지하여 즉시 다음 단계로 교체
  const handleCodeChange = (e) => {
    const value = e.target.value
    if (value.length <= 6) {
      setCode(value)
    }
    if (value.length === 6 && !isLoading) {
      handleVerifyCode(value) // 6자리가 되면 2단계 검증 함수 실행
    }
  }

  // 3단계: 비밀번호 최종 변경 (성공 시 done 화면으로 교체)
  const handleChangePassword = async () => {
    if (isLoading) return // 중복 클릭 방지

    if (newPassword !== confirmPassword) {
      setPasswordError(true)
      return
    }

    setIsLoading(true)
    try {
      // API 요청
      await axios.patch('/api/auth/password/', {
        email,
        password: newPassword,
        repassword: confirmPassword,
      })

      setStep('done') // 완료 화면으로 전환
    } catch (error) {
      const serverMsg = error.response?.data?.message
      alert(serverMsg || '변경에 실패했습니다.')

      if (serverMsg === '비밀번호가 일치하지 않습니다.') {
        setPasswordError(true)
      }
    } finally {
      setIsLoading(false)
    }
  }

  return (
    <div className='pwd-modal__overlay'>
      <div className='pwd-modal'>
        <div className='pwd-modal__header'>
          <h2 className='pwd-modal__title'>비밀번호 변경</h2>
          <button className='pwd-modal__close' type='button' onClick={onClose}>
            ✕
          </button>
        </div>

        {step !== 'done' && <StepIndicator total={3} current={step} />}

        <div className='pwd-modal__body'>
          {/* 이메일 입력 화면 */}
          {step === 1 && (
            <div className='pwd-modal__step-content'>
              <p className='pwd-modal__desc'>가입 시 사용한 이메일을 입력해주세요</p>
              <div className='pwd-modal__row'>
                <InputField
                  id='pwd-email'
                  type='email'
                  placeholder='이메일을 입력하세요'
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  disabled={isLoading}
                />
                <Button
                  variant='primary'
                  size='sm'
                  width='86px'
                  onClick={handleSendEmail}
                  disabled={isLoading}
                >
                  {isLoading ? '...' : '이메일 인증'}
                </Button>
              </div>
              {/* [JS] 나중에 에러 로직 짤 때 조건부로 띄우기 위해 일단 숨겨둠 */}
              {/* <p className='pwd-modal__error'>인증코드가 일치하지 않습니다</p> */}
            </div>
          )}

          {/* 인증코드 입력 화면 */}
          {step === 2 && (
            <div className='pwd-modal__step-content'>
              <p className='pwd-modal__desc'>이메일로 발송된 인증코드를 입력해주세요</p>
              <div className='pwd-modal__row'>
                <InputField
                  id='pwd-code'
                  type='text'
                  placeholder='인증코드 6자리'
                  value={code}
                  onChange={handleCodeChange} // 6자리 감지 시 자동으로 step 3 이동
                  maxLength={6}
                  disabled={isLoading}
                />
                <Button
                  variant='primary'
                  size='sm'
                  width='86px'
                  onClick={handleSendEmail}
                  disabled={isLoading}
                >
                  {isLoading ? '...' : '재발송'}
                </Button>
              </div>
              {codeError && <p className='pwd-modal__error'>인증코드가 일치하지 않습니다</p>}
            </div>
          )}

          {/* 새 비밀번호 입력 화면 */}
          {step === 3 && (
            <div className='pwd-modal__step-content'>
              <p className='pwd-modal__desc'>새로 사용할 비밀번호를 입력해주세요</p>
              <div className='pwd-modal__inputs'>
                <InputField
                  id='pwd-new'
                  type='password'
                  placeholder='새 비밀번호'
                  value={newPassword}
                  onChange={(e) => {
                    setNewPassword(e.target.value)
                    setPasswordError(false)
                  }}
                  disabled={isLoading}
                />
                <InputField
                  id='pwd-confirm'
                  type='password'
                  placeholder='비밀번호 재입력'
                  value={confirmPassword}
                  onChange={(e) => {
                    setConfirmPassword(e.target.value)
                    setPasswordError(false)
                  }}
                  disabled={isLoading}
                />
              </div>
              {passwordError && <p className='pwd-modal__error'>비밀번호가 일치하지 않습니다</p>}
              <div className='pwd-modal__footer'>
                <Button
                  variant='primary'
                  size='sm'
                  width='120px'
                  onClick={handleChangePassword}
                  disabled={isLoading}
                >
                  {isLoading ? '변경 중...' : '변경 완료'}
                </Button>
              </div>
            </div>
          )}

          {/* 완료 화면 */}
          {step === 'done' && (
            <div className='pwd-modal__done'>
              <div className='pwd-modal__done-icon'>
                <img src='/src/assets/step/step-check-icon.svg' alt='완료' />
              </div>
              <p className='pwd-modal__done-title'>비밀번호가 변경됐어요</p>
              <p className='pwd-modal__done-desc'>새 비밀번호로 로그인해 주세요</p>
              <Button
                variant='primary'
                size='sm'
                width='100%'
                onClick={() => {
                  navigate('/login')
                  onClose()
                }}
              >
                로그인 화면으로
              </Button>
            </div>
          )}
        </div>
      </div>
    </div>
  )
}
