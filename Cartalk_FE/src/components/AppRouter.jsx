import { createBrowserRouter } from 'react-router-dom'
import LoginPage from '../pages/loginPage/entry/LoginPage'
import SignupPage from '../pages/signupPage/entry/SignupPage'
import SettingsPage from '../pages/settingsPage/entry/SettingsPage'
import VehicleEditPage from '../pages/vehicleEditPage/entry/VehicleEditPage'
import SearchPage from '../pages/searchPage/entry/SearchPage'
import ChatPage from '../pages/chatPage/entry/ChatPage'

const AppRouter = createBrowserRouter([
  {
    path: '/login',
    element: <LoginPage />,
  },
  {
    path: '/signup',
    element: <SignupPage />,
  },
  {
    path: '/',
    element: <SearchPage />,
  },
  {
    path: '/settings',
    element: <SettingsPage />,
  },
  {
    path: '/vehicle-edit',
    element: <VehicleEditPage />,
  },
  {
    path: '/chat',
    element: <ChatPage />,
  },
])

export default AppRouter
