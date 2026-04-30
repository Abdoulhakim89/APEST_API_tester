import NavBar from './components/NavBar'
import TestForm from './components/TestForm'

import './App.css'

function App() {
  const clicker = ()=> console.log("the heading is clicked")
  let myMethods = [
    "body has property",
    "status code",
    "body has property value"
  ]

  return (
    <>
    <div className='font-[Arial]'>
        <NavBar/>
        <TestForm/>
    </div>

    </>
  )
}

export default App
