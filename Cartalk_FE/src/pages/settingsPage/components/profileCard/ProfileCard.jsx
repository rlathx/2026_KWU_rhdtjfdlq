import Badge from '../../../../components/badge/Badge'
import Button from '../../../../components/button/Button'
import './ProfileCard.css'

export default function ProfileCard({
  nickname,
  status,
  isVerified,
  avatarUrl,
  onEditProfile,
  onEditPersonal,
}) {
  return (
    <div className='profile-card'>
      {/* 아바타 — 사진 없으면 배경색(default), 있으면 이미지 표시 */}
      {avatarUrl ? (
        <img className='profile-card__avatar' src={avatarUrl} alt='프로필' />
      ) : (
        <div className='profile-card__avatar' />
      )}

      <div className='profile-card__info-wrap'>
        <div className='profile-card__info'>
          <p className='profile-card__nickname'>{nickname}</p>
          <p className='profile-card__status'>{status}</p>
        </div>
        {isVerified && <Badge variant='verified' />}
      </div>

      <div className='profile-card__actions'>
        {/* [JS] onEditProfile 연결 */}
        <Button variant='outline' width='110px' onClick={onEditProfile}>
          프로필 편집
        </Button>
        {/* [JS] onEditPersonal 연결 */}
        <Button variant='outline' width='110px' onClick={onEditPersonal}>
          개인정보 편집
        </Button>
      </div>
    </div>
  )
}
