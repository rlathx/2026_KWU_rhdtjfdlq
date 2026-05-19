import Badge from '../../../../components/badge/Badge'
import Button from '../../../../components/button/Button'
import './ChatHeader.css'

export default function ChatHeader({
  plateNumber,
  nickname,
  isVerified,
  avatarUrl,
  safeCallCount,
  onAvatarClick,
  onSafeCall,
  onComplete,
}) {
  return (
    <div className='chat-header'>
      <div className='chat-header__inner'>
        <div className='chat-header__left'>
          {/* 아바타 — 사진 없으면 배경색(default), 있으면 이미지 표시 */}
          {avatarUrl ? (
            <img
              className='chat-header__avatar'
              src={avatarUrl}
              alt='프로필'
              onClick={onAvatarClick} // [JS] 클릭 시 차량 정보 모달 오픈
            />
          ) : (
            <div
              className='chat-header__avatar'
              onClick={onAvatarClick} // [JS] 클릭 시 차량 정보 모달 오픈
            />
          )}
          <div className='chat-header__body'>
            <div className='chat-header__info'>
              <span className='chat-header__plate'>{plateNumber}</span>
              {isVerified && <Badge variant='verified' />}
            </div>
            <span className='chat-header__nickname'>{nickname}</span>
          </div>
        </div>

        <div className='chat-header__buttons'>
          {/* [JS] 안심전화 onClick 연결 */}
          <Button variant='outline-dark' onClick={onSafeCall}>
            안심 전화 {safeCallCount}
          </Button>
          {/* [JS] 완료 onClick 연결 */}
          <Button variant='outline-dark' onClick={onComplete}>
            완료
          </Button>
        </div>
      </div>
    </div>
  )
}
