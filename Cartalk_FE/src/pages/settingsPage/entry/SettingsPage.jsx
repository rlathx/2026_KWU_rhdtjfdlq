import { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import axios from 'axios'
import Sidebar from '../../../components/sidebar/Sidebar'
import ProfileCard from '../components/profileCard/ProfileCard'
import { VehicleCard, VehicleAddCard } from '../components/vehicleCard/VehicleCard'
import ProfileSettingsModal from '../components/profileSettingsModal/ProfileSettingsModal'
import PersonalSettingsModal from '../components/personalSettingsModal/PersonalSettingsModal'
import './SettingsPage.css'

export default function SettingsPage() {
  const [isProfileModalOpen, setIsProfileModalOpen] = useState(false)
  const [isPersonalModalOpen, setIsPersonalModalOpen] = useState(false)
  const [isVehicleEditing, setIsVehicleEditing] = useState(false)
  const navigate = useNavigate()

  // 초기값을 localStorage에서 읽어서 세팅
  const [profileData, setProfileData] = useState({
    email: localStorage.getItem('user_email') || '',
    nickName: localStorage.getItem('user_nickname') || '',
    message: localStorage.getItem('user_message') || '',
    profile: localStorage.getItem('user_profile') || '',
    isVerified: false,
  })

  useEffect(() => {
    const fetchProfile = async () => {
      const email = localStorage.getItem('user_email')
      if (!email) {
        console.error('email이 없습니다')
        return
      }

      try {
        const response = await axios.get(`/api/user/profile/${email}`)
        console.log('프로필 조회 성공:', response.data)

        setProfileData({
          email: email,
          nickName: response.data.nickName,
          message: response.data.message,
          profile: response.data.profile,
          isVerified: response.data.isVerified || false,
        })
        localStorage.setItem('user_nickname', response.data.nickName || '')
        localStorage.setItem('user_message', response.data.message || '')
        localStorage.setItem('user_profile', response.data.profile || '')
      } catch (error) {
        console.error('프로필 조회 실패:', error)
        // API 실패해도 localStorage 값 그대로 유지 (초기값으로 세팅)
        setProfileData({
          email: localStorage.getItem('user_email') || '',
          nickName: localStorage.getItem('user_nickname') || '',
          message: localStorage.getItem('user_message') || '',
          profile: localStorage.getItem('user_profile') || '',
          isVerified: false,
        })
      }
    }

    fetchProfile()
  }, [])

  // 차량 목록 state로 관리
  const [vehicles, setVehicles] = useState([])

  useEffect(() => {
    const userId = localStorage.getItem('user_id')
    if (!userId) return

    // 차량 목록 조회
    const fetchMyCars = async () => {
      try {
        const response = await axios.get('/api/user', {
          params: { userId: Number(userId) },
        })
        setVehicles(response.data || [])
      } catch (error) {
        console.error('차량 목록 조회 실패:', error)
        setVehicles([])
      }
    }

    fetchMyCars()
  }, [])

  const handleVehicleSave = () => {
    setIsVehicleEditing(false)
  }

  return (
    <div className='settings'>
      <Sidebar />

      <main className='settings__main'>
        <h1 className='settings__title'>프로필 설정</h1>

        <ProfileCard
          nickname={profileData.nickName}
          status={profileData.message}
          isVerified={profileData.isVerified}
          avatarUrl={profileData.profile}
          onEditProfile={() => setIsProfileModalOpen(true)}
          onEditPersonal={() => setIsPersonalModalOpen(true)}
        />

        <div className='settings__divider' />

        <div className='settings__vehicle-header'>
          <h2 className='settings__vehicle-title'>내 차량</h2>
          {isVehicleEditing ? (
            <button className='settings__vehicle-save' onClick={handleVehicleSave}>
              차량 저장
            </button>
          ) : (
            <button className='settings__vehicle-edit' onClick={() => setIsVehicleEditing(true)}>
              차량 편집
            </button>
          )}
        </div>

        <div className='settings__vehicle-list'>
          {vehicles.map((v) => (
            <VehicleCard
              key={v.carId}
              plateNumber={v.carNum}
              type={v.vehicleType}
              note={v.comment}
              imageUrl={v.carProfile}
              isVerified={true}
              isEditing={isVehicleEditing}
              onClick={() => navigate('/vehicle-edit')}
            />
          ))}
          {isVehicleEditing && <VehicleAddCard onClick={() => navigate('/vehicle-edit')} />}
        </div>
      </main>

      {/* 모달 연동 부분 */}
      {isProfileModalOpen && (
        <ProfileSettingsModal
          isOpen={isProfileModalOpen}
          onClose={() => setIsProfileModalOpen(false)}
          initialData={{
            ...profileData,
            email: profileData.email || localStorage.getItem('user_email'), // ← 보험 추가
          }}
          onSuccess={(updatedData) => {
            // localStorage에도 저장해서 재렌더링 시 유지
            if (updatedData.nickName !== undefined) {
              localStorage.setItem('user_nickname', updatedData.nickName)
            }
            if (updatedData.message !== undefined) {
              localStorage.setItem('user_message', updatedData.message)
            }
            if (updatedData.profile !== undefined) {
              localStorage.setItem('user_profile', updatedData.profile)
            }
            setProfileData((prev) => ({ ...prev, ...updatedData }))
            setIsProfileModalOpen(false)
          }}
        />
      )}

      {isPersonalModalOpen && (
        <PersonalSettingsModal
          onClose={() => setIsPersonalModalOpen(false)}
          initialData={{
            name: profileData.name || '',
            phoneNumber: profileData.phoneNumber || '',
          }}
        />
      )}
    </div>
  )
}
