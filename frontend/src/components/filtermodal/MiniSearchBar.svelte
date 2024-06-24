<script>
    import { format, addDays, differenceInDays } from 'date-fns'; // addDays 추가
    import GuestCountSelection from "./GuestCountSelection.svelte";
    import PriceRangeSelection from "./PriceRangeSelection.svelte";
    import ScheduleSelection from "./ScheduleSelection.svelte";
    import filterBtn from "$lib/assets/image/filterBtnSmall.svg"
    import { onMount } from 'svelte';

    export let checkIn;
    export let checkOut;
    export let selectedMinPrice = 100000;
    export let selectedMaxPrice = 1000000;
    export let totalGuests = 0;
    export let state = false;

    let dateFormat = 'M월 d일';
    let onDatePickerPopup = false;
    let onRatePopup = false;
    let onGuestPopup = false;
    const dowLabels = ["일", "월", "화", "수", "목", "금", "토"];
    const monthLabels = ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"];

    const toggleDatePicker = () => {
        onDatePickerPopup = !onDatePickerPopup;
    };

    const toggleRatePopup = () => {
        onRatePopup = !onRatePopup;
    };

    const toggleGuestPopup = () => {
        onGuestPopup = !onGuestPopup;
    };

    const formatDate = (dateString) => (dateString && format(new Date(dateString), dateFormat)) || '';

    $: formattedCheckIn = formatDate(checkIn);
    $: formattedCheckOut = formatDate(checkOut);
    $: formattedDates = state ? '언제든지' : (formattedCheckIn && formattedCheckOut ? `${formattedCheckIn} - ${formattedCheckOut}` : '일정 입력');

    const handleDateSelected = (e) => {
        const { startDate, endDate } = e.detail;
        checkIn = startDate;
        checkOut = endDate;
        toggleDatePicker();
    };

    const handleRateSelected = (min, max) => {
        selectedMinPrice = min;
        selectedMaxPrice = max;
        toggleRatePopup();
    };

    const handleGuestsSelected = (total) => {
        totalGuests = total;
        toggleGuestPopup();
    };

    const handleSearch = () => {
        // 현재 날짜와 1주일 후의 날짜 계산
        const now = new Date();
        const defaultCheckInDate = format(now, 'yyyy-MM-dd');
        const defaultCheckOutDate = format(addDays(now, 7), 'yyyy-MM-dd');

        // 입력된 값이 없을 때 기본 날짜로 설정
        const checkInDate = checkIn ? format(new Date(checkIn), 'yyyy-MM-dd') : defaultCheckInDate;
        const checkOutDate = checkOut ? format(new Date(checkOut), 'yyyy-MM-dd') : defaultCheckOutDate;

        // 체류 기간 계산
        const length = differenceInDays(new Date(checkOutDate), new Date(checkInDate));

        // URL 생성
        const url = `/accommodations?checkin=${checkInDate}&checkout=${checkOutDate}&length=${length}&capacity=${totalGuests}&price_min=${selectedMinPrice}&price_max=${selectedMaxPrice}`;
        window.location.href = url;
    };
</script>

<div class="relative flex justify-center min-w-[700px]">
    <div class="bg-white shadow-md rounded-full flex items-center w-full max-w-lg p-2 border border-gray-300">
        <button type="button" class="flex-grow px-4 py-2 border-r text-left" on:click={toggleDatePicker}>
            <div class="text-sm text-gray-600">{formattedDates}</div>
        </button>
        <button type="button" class="flex-grow px-4 py-2 border-r text-left" on:click={toggleRatePopup}>
            <div class="text-sm text-gray-600">₩{selectedMinPrice.toLocaleString()} - ₩{selectedMaxPrice.toLocaleString()}</div>
        </button>
        <div class="flex">
            <button type="button" class="flex-grow px-4 py-2 text-left" on:click={toggleGuestPopup}>
                <div class="text-sm text-gray-600">게스트 {totalGuests}명</div>
            </button>
            <button class="p-2 focus:outline-none" on:click={handleSearch}>
                <img src="{filterBtn}" alt="filter button" class="w-6 h-6">
            </button>
        </div>
    </div>

    {#if onDatePickerPopup}
        <ScheduleSelection
                bind:checkIn={checkIn}
                bind:checkOut={checkOut}
                {dowLabels}
                {monthLabels}
                {onDatePickerPopup}
                on:dateSelected={handleDateSelected}
                on:toggle={toggleDatePicker}
        />
    {/if}

    {#if onRatePopup}
        <PriceRangeSelection bind:selectedMinPrice={selectedMinPrice} bind:selectedMaxPrice={selectedMaxPrice} onClose={handleRateSelected} />
    {/if}

    {#if onGuestPopup}
        <GuestCountSelection bind:total={totalGuests} onClose={handleGuestsSelected} />
    {/if}
</div>

<style>
    .border-r {
        border-right: 1px solid #e5e7eb; /* Tailwind's gray-300 color */
    }
</style>