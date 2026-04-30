
function NavBar(){
    return(
    <>
        <div className="flex fixed w-full justify-between px-16 py-8 bg-gray-100">
            <h1 className="text-2xl text-green-800 font-bold">APEST</h1>
            <div className="flex gap-4">
                <button className="px-4 py-2 rounded-xl text-md font-semibold bg-green-700 text-blue-100 cursor-pointer">Run Suite</button>
                <button className="px-4 py-2 rounded-xl text-md font-semibold bg-black text-blue-100 cursor-pointer">Save Suite</button>
            </div>
        </div>
    </>

    ) 
}

export default NavBar;