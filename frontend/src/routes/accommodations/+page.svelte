<script>
    import AccoCard from "./AccoCard.svelte";
    import {onMount} from "svelte";
    import profile_icon from "$lib/assets/image/profile_icon.svg";
    import LoginModal from "./LoginModal.svelte";
    import ReservationModal from "./ReservationModal.svelte";
    import ProfilePopup from "../../components/ProfilePopup.svelte";
    import {isLoggedIn} from "../../store/Auth.js";
    import {accoProducts} from "../../store/AccoProducts.js";
    import MiniSearchBar from "../../components/filtermodal/MiniSearchBar.svelte";

    onMount(() => {
        const container = document.getElementById('map');
        const options = {
            center: new kakao.maps.LatLng(37.490823, 127.033435),
            level: 3
        };
        const map = new kakao.maps.Map(container, options);

        // 지도를 클릭한 위치에 표출할 마커입니다
        let marker = new kakao.maps.Marker({
            // 지도 중심좌표에 마커를 생성합니다
            position: map.getCenter()
        });
        // 지도에 마커를 표시합니다
        marker.setMap(map);

        // 지도에 클릭 이벤트를 등록합니다
        // 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
        kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
            // 클릭한 위도, 경도 정보를 가져옵니다
            let latlng = mouseEvent.latLng;

            // 마커 위치를 클릭한 위치로 옮깁니다
            marker.setPosition(latlng);
            let message = '위도는 ' + latlng.getLat() + ' 이고, ';
            message += '경도는 ' + latlng.getLng() + ' 입니다';
            let resultDiv = document.getElementById('clickLatlng');
            resultDiv.innerHTML = message;})
    })

    $: isLoginBtnClicked = false;
    const handleLoginBtnClick = () => {
        isLoginBtnClicked = !isLoginBtnClicked;
    }

    $: isProfileBtnClicked = false;
    const handleProfileBtnClick = () => {
        isProfileBtnClicked = !isProfileBtnClicked;
    }

    $: isReservationModalOpened = false;
    const handleReservationModalClicked = () => {
        isReservationModalOpened = !isReservationModalOpened;
    }

    let item = {
        location: "간단한 지역명 + 숙소 카테고리",
        title: "숙소 제목",
        floorPlan: {maxGuestCount: 3, roomCount:1 ,bedCount:1, bathroomCount:1},
        amenities: ["주방", "무선 인터넷", "에어컨", "헤어드라이어"],
        pricePerNight: 82953,
        totalPrice: 1729707,
    }
    let items = Array.from({length:10}, ()=>({...item}));

</script>

<div class="flex flex-col h-screen">
    <header class="flex flex-col relative z-2 mb-10">
        <div id="logo" class="max-w-[1440px] mx-auto w-full flex items-center justify-between p-4 py-6 border-b ">
            <a class="text-2xl font-semibold" href="/accommodations">Airdnb</a>
            <nav class="hidden md:flex items-center gap-[30px] lg:gap-6">
                <MiniSearchBar/>
            </nav>
            <div class="border border-gray-300 rounded-full flex gap-2 relative">
                <button class="hidden md:flex place-items-center m-1 pl-3">
                    <i class="fa-solid fa-bars"></i>
                </button>
                <button class="md:hidden place-items-center m-1 px-3">
                    <i class="fa-solid fa-bars"></i>
                </button>
                <button class="hidden md:flex m-0.5" on:click={$isLoggedIn ? handleProfileBtnClick : handleLoginBtnClick}>
                    <img src="{profile_icon}" alt="User Profile Icon">
                </button>
                {#if isProfileBtnClicked && $isLoggedIn}
                    <ProfilePopup bind:isProfileBtnClicked="{isProfileBtnClicked}"/>
                {/if}
            </div>
        </div>
    </header>
    <section class="max-w-[1440px] mx-auto w-full flex flex-1" style="height: 90vh">
        <section class="flex gap-1 min-w-[600px] w-[720px] flex-wrap overflow-scroll mx-2">
            {#each items as _}
            <AccoCard
                location={item.location}
                title={item.title}
                floorPlan={item.floorPlan}
                amenities={item.amenities}
                pricePerNight={item.pricePerNight}
                totalPrice={item.totalPrice}
            />
            {/each}
        </section>
        <section>
            <div id="map" class="w-[720px] h-screen z-10"></div>
            <div id="clickLatlng" class="mt-4"></div>
        </section>
    </section>
    {#if isLoginBtnClicked && !$isLoggedIn}
        <LoginModal bind:isLoginBtnClicked="{isLoginBtnClicked}"/>
    {/if}
    {#if isReservationModalOpened}
        <ReservationModal
                checkIn=""
                checkOut=""
                totalGuests=""
                totalPrice=""
                bind:isAccoCardClicked="{isReservationModalOpened}"/>
    {/if}
</div>